package com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.model.Tweet;

import java.io.IOException;
import java.util.ArrayList;

public class TweetDeserializer extends StdDeserializer {

    public TweetDeserializer() {
        this(null);
    }

    public TweetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Tweet deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode tweetNode = jp.getCodec().readTree(jp);
        var authorId = tweetNode.get("data").get("author_id").asText();
        var createdAt = tweetNode.get("data").get("created_at").asText();

        var tweetIds = new ArrayList<String>();
        ArrayNode idsNode = (ArrayNode) tweetNode.get("data").get("edit_history_tweet_ids");
        idsNode.elements().forEachRemaining(nodeIds -> tweetIds.add(nodeIds.asText()));

        var id = tweetNode.get("data").get("id").asText();
        var text = tweetNode.get("data").get("text").asText();
        return new Tweet(authorId, createdAt, tweetIds, id, text);

    }

}
