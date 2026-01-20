package com.artificialintelligence.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AIAssistantService {

    private static final Logger logger = LoggerFactory.getLogger(AIAssistantService.class);

    private final GeminiService geminiService;

    public AIAssistantService(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    public String handleQuestion(String question) {
        logger.info("Anonymous prompt logged for training: {}", question);
        return geminiService.generate(question);
    }
}
