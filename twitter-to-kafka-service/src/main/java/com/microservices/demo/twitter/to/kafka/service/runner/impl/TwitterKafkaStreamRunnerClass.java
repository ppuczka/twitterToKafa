package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Log4j2
public class TwitterKafkaStreamRunnerClass implements StreamRunner {

    private final TwitterToKafkaServiceConfigData configData;
    private final TwitterKafkaStatusListener listener;

    private TwitterStream stream;

    @Override
    public void run() {
        stream = new TwitterStreamFactory().getInstance();
        stream.addListener(listener);
        addFilter();
    }
    @PreDestroy
    public void shutdown() {

    }

    private void addFilter() {
        String[] keywords = configData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery query = new FilterQuery(keywords);
        stream.filter(query);
        log.info("Started filtering stream for configured keywords: {}", Arrays.toString(keywords));
    }
}
