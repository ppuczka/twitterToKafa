package com.prpu.microservicesdemo.twitter.to.kafka.service.runner;

import twitter4j.TwitterException;

public interface StreamRunner {

    void run() throws TwitterException, InterruptedException;
}
