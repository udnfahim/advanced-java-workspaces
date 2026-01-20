package com.artificialintelligence.controller;

import com.artificialintelligence.service.AIAssistantService;
import com.artificialintelligence.service.GeminiService;
import com.artificialintelligence.service.PromptTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final GeminiService geminiService;
    private final PromptTemplateService promptTemplateService;
    private final AIAssistantService aiAssistantService;

    @Autowired
    public AIController(GeminiService geminiService,
                        PromptTemplateService promptTemplateService,
                        AIAssistantService aiAssistantService) {
        this.geminiService = geminiService;
        this.promptTemplateService = promptTemplateService;
        this.aiAssistantService = aiAssistantService;
    }

    // Task 1: General prompt
    @GetMapping("/generate")
    public String generateGet(@RequestParam String prompt) {
        return geminiService.generate(prompt);
    }

    // Task 2: Personalized prompt
    @GetMapping("/personalized")
    public String personalizedResponseGet(@RequestParam String userName, @RequestParam String topic) {
        String prompt = promptTemplateService.buildPrompt(userName, topic);
        return geminiService.generate(prompt);
    }

    // Task 3: AI assistant with logging
    @GetMapping("/assistant")
    public String askAssistantGet(@RequestParam String question) {
        return aiAssistantService.handleQuestion(question);
    }

    @GetMapping("/generate/test")
    public String testGenerate() {
        return "AI Generate endpoint is running!";
    }

    @GetMapping("/personalized/test")
    public String testPersonalized() {
        return "AI Personalized endpoint is running!";
    }

    @GetMapping("/assistant/test")
    public String testAssistant() {
        return "AI Assistant endpoint is running!";
    }

}
