package org.example.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
 public class TrainMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainMessageService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        sendMessageWithRetry(message, 3);
    }

    private void sendMessageWithRetry(String message, int retryCount) {

        if (retryCount > 3) {
            LOGGER.error("No more retries for message=[" + message + "]");
            return;
        }

        String TOPIC = "train";
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                LOGGER.debug("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                LOGGER.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
                sendMessageWithRetry(message, retryCount + 1);
            }
        });
    }
}
