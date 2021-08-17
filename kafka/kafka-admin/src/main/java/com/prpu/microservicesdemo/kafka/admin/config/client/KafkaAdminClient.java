package com.prpu.microservicesdemo.kafka.admin.config.client;

import com.prpu.microservicesdemo.config.KafkaConfigData;
import com.prpu.microservicesdemo.config.RetryConfigData;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@AllArgsConstructor
public class KafkaAdminClient {

    private final RetryConfigData retryConfigData;

    private final KafkaConfigData kafkaConfigData;

    private final AdminClient adminClient;

    private final RetryTemplate retryTemplate;
}
