package com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.runner.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.model.Tweet;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.runner.StreamRunner;
import com.prpu.microservicesdemo.config.TwitterToKafkaServiceConfigData;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.AddOrDeleteRulesRequest;
import com.twitter.clientlib.model.AddRulesRequest;
import com.twitter.clientlib.model.RuleNoId;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Log4j2
@ConditionalOnProperty(name = "enable-v2-twitter-client", havingValue = "true", matchIfMissing = true)
public class TwitterKafkaApiV2Runner implements StreamRunner {


    private final TwitterToKafkaServiceConfigData configData;
    private final TwitterKafkaStatusListener listener;
    private final TwitterApi twitterApi;


    public TwitterKafkaApiV2Runner(TwitterToKafkaServiceConfigData configData, TwitterKafkaStatusListener listener) {
        this.configData = configData;
        this.listener = listener;
        this.twitterApi = new TwitterApi(new TwitterCredentialsBearer(configData.getTwitterOAuth2ClientBearerToken()));
    }

    @Override
    public void run() throws ApiException, IOException {
        log.info("Twitter api v2");

        List<RuleNoId> rulesList = new ArrayList<>();
        configData.getTwitterKeywords().forEach(keyword -> rulesList.add(new RuleNoId().value(keyword)));

        var addOrDeleteRulesRequest = new AddOrDeleteRulesRequest();
        addOrDeleteRulesRequest.setActualInstance(new AddRulesRequest().add(rulesList));

        twitterApi.tweets().addOrDeleteRules(addOrDeleteRulesRequest).execute();

        Set<String> tweetFields = new HashSet<>();
        tweetFields.add("author_id");
        tweetFields.add("id");
        tweetFields.add("created_at");
        tweetFields.add("text");

        Set<String> expansions = new HashSet<>();
        expansions.add("referenced_tweets.id");

        var stream = twitterApi.tweets()
                .searchStream()
                .tweetFields(tweetFields)
//                .expansions(expansions)
                .execute();
        ObjectMapper objectMapper = new ObjectMapper();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = reader.readLine();
        while (line != null) {
            if(line.isEmpty()) {
                line = reader.readLine();
                continue;
            }
            var tweet = objectMapper.readValue(line, Tweet.class);
            listener.onV2Status(tweet.text());
            line = reader.readLine();

        }
    }
}