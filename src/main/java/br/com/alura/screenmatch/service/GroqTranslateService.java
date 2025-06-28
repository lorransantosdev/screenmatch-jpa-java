package br.com.alura.screenmatch.service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public class GroqTranslateService {

    private static final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.groq.com/openai/v1")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + System.getenv("AI_GROQ_API_KEY"))
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .build();

    public static String translatorText(String text) {
        Map<String, Object> body = Map.of(
                "model", "meta-llama/llama-4-scout-17b-16e-instruct",
                "messages", List.of(
                        Map.of("role", "user", "content", "Traduza para o português: " + text)
                )
        );

        Map response = webClient.post()
                .uri("/chat/completions")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

        return message.get("content").toString().trim();
    }
}