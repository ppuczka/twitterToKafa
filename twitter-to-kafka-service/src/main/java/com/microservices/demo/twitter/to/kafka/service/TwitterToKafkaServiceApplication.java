package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.runner.impl.TwitterKafkaStreamRunnerClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@Log4j2
public class TwitterToKafkaServiceApplication implements CommandLineRunner {


    private final TwitterToKafkaServiceConfigData configData;
    private final TwitterKafkaStreamRunnerClass streamRunnerClass;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running...");
        log.info(configData.getWelcomeMessage());
        log.info(Arrays.toString(configData.getTwitterKeywords().toArray(new String[] {})));
        streamRunnerClass.run();
    }
}
