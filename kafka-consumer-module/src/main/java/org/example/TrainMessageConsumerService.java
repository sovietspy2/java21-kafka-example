package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TrainMessageConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TrainMessageConsumerService.class);

  @Autowired
  private DLQCache dlqCache;

  @KafkaListener(topics = "text", groupId = "foo", containerFactory = "notTheDefaultKafkaListenerContainerFactory")
  private void listenGroupFoo(@Payload String message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
    LOGGER.debug("Received Message in group foo: " + message + " from partition: " + partition);
  }

  @KafkaListener(topics = "train", groupId = "testGroup1", containerFactory = "kafkaTrainMessageListenerFactory", properties = {
      "auto.offset.reset=earliest"})
  private void listenToTrains(@Payload TrainMessage message) {

    if (dlqCache.get(message) != null && dlqCache.get(message) > 3) {
      LOGGER.warn("Message is moved to DLQ" + message);

      dlqCache.evict(message);

      // TODO: SOME LOGIC TO MOVE TO DLQ
      return;
    }

    if (message.hasError()) {
      // simulation a situation where we get an error during processing the message
      dlqCache.put(message);

      LOGGER.error("Received Message in group testGroup: " + message);
      throw new RuntimeException("Error occurred during processing the message");
      //
    } else {
      LOGGER.debug("Received Message in group bar: " + message);
    }
  }

}
