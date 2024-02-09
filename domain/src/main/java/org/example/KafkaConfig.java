package org.example;

import org.springframework.beans.factory.annotation.Value;

public class KafkaConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    protected String bootstrapAddress;
}
