package com.prpu.microservicesdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {

    private List<String> twitterKeywords;
    private String welcomeMessage;

    private Integer mockMinTweetLength;
    private Integer mockMaxTweetLength;
    private Long mockSleepMs;
    private Boolean enableMockTweets;

    private String twitterOAuth2ClientId;
    private String twitterOAuth2ClientSecret;
    private String twitterOAuth2ClientAccessToken;
    private String twitterOAuth2ClientRefreshToken;
    private String twitterOAuth2ClientBearerToken;


}
