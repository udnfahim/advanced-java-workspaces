package com.springsecurity.controller;

import com.springsecurity.model.Role;
import com.springsecurity.service.JwtService;
import com.springsecurity.service.MailService;
import com.springsecurity.service.UserService;
import com.springsecurity.util.TurnstileResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final MailService mailService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final Map<String, OtpEntry> otpStore = new HashMap<>();

    public AuthController(UserService userService, JwtService jwtService, MailService mailService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.mailService = mailService;
    }

    //Bot Protection According to cloudflare Documentation
    private boolean verifyTurnstile(String token) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", "0x4AAAAAACTkeMbnrEssNO8v3JkiP_rcO5s");
        map.add("response", token);

        TurnstileResponse response = restTemplate.postForObject(
                "https://challenges.cloudflare.com/turnstile/v0/siteverify",
                map,
                TurnstileResponse.class
        );

        return response != null && response.success;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = false) boolean rememberMe,
                          @RequestParam("cf-turnstile-response") String turnstileToken,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes) {

        if (!verifyTurnstile(turnstileToken)) {
            redirectAttributes.addFlashAttribute("error", "Bot verification failed");
            return "redirect:/login";
        }

        try {
            var user = userService.authenticate(username, password);

            long expirationMillis = rememberMe ? 3 * 60 * 60 * 1000L : 10 * 60 * 1000L;

            String token = jwtService.generateToken(user.getUsername(), user.getRoles(), rememberMe);

            Cookie cookie = new Cookie("JWT", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge((int) (expirationMillis / 1000));
            response.addCookie(cookie);

            return "redirect:/dashboard";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session, org.springframework.ui.Model model) {

        String email = (String) session.getAttribute("otpEmail");
        if (email != null) model.addAttribute("prefillEmail", email);

        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String fullName,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String mobile,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Passwords do not match");
            return "redirect:/register";
        }

        try {
            String otp = mailService.sendOtp(email);

            otpStore.put(email, new OtpEntry(fullName, username, email, mobile, password, otp, Instant.now()));

            session.setAttribute("otpEmail", email);

            redirectAttributes.addFlashAttribute("success", "OTP sent to your email. Enter it to complete registration.");
            return "redirect:/register/otp";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/register/otp")
    public String otpPage(HttpSession session, org.springframework.ui.Model model, RedirectAttributes redirectAttributes) {

        String email = (String) session.getAttribute("otpEmail");
        if (email == null || !otpStore.containsKey(email)) {
            redirectAttributes.addFlashAttribute("error", "No registration in progress. Please register first.");
            return "redirect:/register";
        }

        model.addAttribute("otpEmail", email);
        return "register-otp";
    }

    @PostMapping("/register/otp")
    public String verifyOtp(@RequestParam String otp,
                            @RequestParam String email,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        OtpEntry entry = otpStore.get(email);
        if (entry == null) {
            redirectAttributes.addFlashAttribute("error", "No OTP found for this email.");
            return "redirect:/register";
        }

        long elapsed = Instant.now().toEpochMilli() - entry.timestamp.toEpochMilli();

        if (!entry.otp.equals(otp) || elapsed > 5 * 60 * 1000) { // 5 minutes

            redirectAttributes.addFlashAttribute("error", "Invalid or expired OTP.");
            return "redirect:/register/otp";
        }

        userService.register(entry.fullName, entry.username, entry.email, entry.mobile, entry.password, Role.USER);

        otpStore.remove(email);
        session.removeAttribute("otpEmail");

        redirectAttributes.addFlashAttribute("success", "Your account has been verified. You can login now.");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("JWT", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login?logout";
    }

    private static class OtpEntry {
        String fullName;
        String username;
        String email;
        String mobile;
        String password;
        String otp;
        Instant timestamp;

        public OtpEntry(String fullName, String username, String email,
                        String mobile, String password, String otp, Instant timestamp) {
            this.fullName = fullName;
            this.username = username;
            this.email = email;
            this.mobile = mobile;
            this.password = password;
            this.otp = otp;
            this.timestamp = timestamp;
        }
    }
}
