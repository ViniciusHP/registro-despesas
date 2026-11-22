package com.vhp.budgeting.infrastructure.ollama;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class OllamaWarmupRunner implements ApplicationRunner {

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    @Value("${spring.ai.openai.audio.transcription.model}")
    private String transcriptionModel;

    @Value("${spring.ai.openai.chat.model}")
    private String chatModel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var models = List.of(this.transcriptionModel, this.chatModel);

        for (var model : models) {
            warmup(model);
        }
    }

    private void warmup(String model) {
        try{
            var factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(Duration.ofSeconds(10));
            factory.setReadTimeout(Duration.ofMinutes(5));

            RestClient restClient = RestClient.builder()
                    .baseUrl(this.baseUrl.replaceAll("/v1/?$", ""))
                    .requestFactory(factory)
                    .build();

            Map<String, Object> body = Map.of(
                    "model", model,
                    "messages", List.of(Map.of("role", "user", "content", "Olá")),
                    "stream", false,
                    "keep_alive", -1
            );

            restClient.post()
                    .uri("/api/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity();

            log.info("Warmup do modelo {} concluído com sucesso!", model);
        } catch (Exception e) {
            log.error("Falha no warmup do modelo {}.", model);
        }
    }
}
