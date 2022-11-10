package com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.model.deserializer.TweetDeserializer;

import java.util.List;

@JsonDeserialize(using = TweetDeserializer.class)
public record Tweet(
        @JsonProperty("author_id") String authorId,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("edit_history_tweet_ids") List<String> editHistoryTweetId,
        String id,
        String text
) {
}

