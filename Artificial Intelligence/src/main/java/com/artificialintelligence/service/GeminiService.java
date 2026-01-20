package com.artificialintelligence.service;

import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final Client client;

    public GeminiService(
            @Value("${spring.ai.google.genai.api-key}") String apiKey
    ) {
        this.client = Client.builder()
                .apiKey(apiKey)
                .build();
    }

    //flash preview gemini
    public String generate(String prompt) {
        return client.models
                .generateContent("gemini-3-flash-preview", prompt, null)
                .text();
    }
}
