package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TrainConsumerService {
    private static Logger LOGGER = LoggerFactory.getLogger(TrainConsumerService.class);

    @KafkaListener(topics = "text", groupId = "foo", containerFactory = "notTheDefaultKafkaListenerContainerFactory")
    private void listenGroupFoo(@Payload  String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        LOGGER.debug("Received Message in group foo: " + message +" from partition: " + partition);
    }

//    @KafkaListener(topics="train", groupId = "testGroup", containerFactory = "kafkaTrainMessageListenerFactory")
//    private void listenToTrains(@Payload TrainMessage message) {
//        LOGGER.debug("Received Message in group bar: " + message);
//    }

}
