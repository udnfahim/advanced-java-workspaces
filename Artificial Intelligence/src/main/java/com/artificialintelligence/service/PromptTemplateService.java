package com.artificialintelligence.service;

import org.springframework.stereotype.Service;

@Service
public class PromptTemplateService {

    public String buildPrompt(String userName, String topic) {
        return String.format("Hello %s! Can you provide insights on %s?", userName, topic);
    }
}
