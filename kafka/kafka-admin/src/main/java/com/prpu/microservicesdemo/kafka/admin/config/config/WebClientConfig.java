package com.prpu.microservicesdemo.kafka.admin.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

}
