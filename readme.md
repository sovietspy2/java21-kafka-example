# Spring Boot example with Kafka

## Setup:
1. `cd kafka`
2. `docker-compose up -d`

## Run:
1. `mvn -pl kafka-producer-module spring-boot:run`
2. `mvn -pl kafka-consumer-module spring-boot:run`

## Features:

- sending text message to kafka
- sending POJO messag to kafka (json)
- consuming single and JSON messages from kafka
- consuming messages from multiple topics
- kafka message error handling with DLQ and retry logic