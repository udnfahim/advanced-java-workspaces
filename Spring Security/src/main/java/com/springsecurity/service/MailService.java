package com.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String adminMail= "hewhoremains.trainman@gmail.com";

    private String lastOtp;

    private Instant otpTimeStamp;

    public String sendOtp(String clint){

        String otp = String.format("%05d",new Random().nextInt(100000));

        this.lastOtp=otp;
        this.otpTimeStamp=Instant.now();

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(adminMail);
        message.setTo(clint);
        String subject = "Your OTP for Nagorik Portal Registration";
        String body = "Hello,\n\n"
                + "Your One-Time Password (OTP) for completing your registration on Spring Security is:\n\n"
                + otp + "\n\n"
                + "This OTP is valid for the next 1 minutes. Please do not share it with anyone.\n\n"
                + "If you did not request this, please ignore this email.\n\n"
                + "Thank you,\n"
                + "Spring Scurity Team";

        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);

        return otp;
    }
}
