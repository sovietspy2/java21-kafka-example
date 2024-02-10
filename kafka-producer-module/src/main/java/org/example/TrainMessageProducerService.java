package org.example;

import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class TrainMessageProducerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrainMessageProducerService.class);

  @Autowired
  private KafkaTemplate<String, TrainMessage> trainKafkaTemplate;

  @Autowired
  private KafkaTemplate<String, String> textKafkaTemplate;

  public void sendSimpleMessage() {
    textKafkaTemplate.send("text", "Hello, World!");
  }

  public void sendMessage(TrainMessage message) {
    sendMessageWithRetry(message, 3);
  }

  private void sendMessageWithRetry(TrainMessage message, int retryCount) {

    if (retryCount > 3) {
      LOGGER.error("No more retries for message=[" + message + "]");
      return;
    }

    String TOPIC = "train";
    CompletableFuture<SendResult<String, TrainMessage>> future = trainKafkaTemplate.send(TOPIC,
        message);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        LOGGER.debug(
            "Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset()
                + "]");
      } else {
        LOGGER.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
        sendMessageWithRetry(message, retryCount + 1);
      }
    });
  }
}
