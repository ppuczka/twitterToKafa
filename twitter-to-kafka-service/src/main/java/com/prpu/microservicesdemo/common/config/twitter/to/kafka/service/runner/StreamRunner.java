package com.prpu.microservicesdemo.common.config.twitter.to.kafka.service.runner;

import com.twitter.clientlib.ApiException;
import twitter4j.TwitterException;

import java.io.IOException;

public interface StreamRunner {

    void run() throws TwitterException, InterruptedException, ApiException, IOException;
}
