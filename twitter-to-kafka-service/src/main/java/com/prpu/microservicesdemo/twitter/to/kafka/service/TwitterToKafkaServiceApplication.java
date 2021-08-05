package com.prpu.microservicesdemo.twitter.to.kafka.service;

import com.prpu.microservicesdemo.config.TwitterToKafkaServiceConfigData;
import com.prpu.microservicesdemo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@Log4j2
@ComponentScan(basePackages = "com.prpu")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {


    private final TwitterToKafkaServiceConfigData configData;

    private final StreamRunner streamRunner;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running...");
        log.info(configData.getWelcomeMessage());
        log.info("Starting mock filtering twitter streams for keywords {}",
                Arrays.toString(configData.getTwitterKeywords().toArray(new String[] {})));
        streamRunner.run();
    }
}
