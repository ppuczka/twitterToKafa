package com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.runner.impl;

import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.runner.StreamRunner;
import com.prpu.microservicesdemo.config.TwitterToKafkaServiceConfigData;
import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;

@Component
@RequiredArgsConstructor
public class TwitterKafkaApiV2Runner implements StreamRunner {


    private final TwitterToKafkaServiceConfigData configData;
    private final TwitterKafkaStatusListener listener;

    private TwitterStream stream;

    @Override
    public void run() throws TwitterException, InterruptedException {
        var twitterApi = new TwitterApi(new TwitterCredentialsOAuth2(
                configData.getTwitterOAuth2ClientId(),
                configData.getTwitterOAuth2ClientSecret(),
                configData.getTwitterOAuth2ClientAccessToken(),
                configData.getTwitterOAuth2ClientRefreshToken()
        ));

    }
}
