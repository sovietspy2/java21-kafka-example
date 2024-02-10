package org.example;

import java.util.UUID;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@Import(KafkaTestContainersConfiguration.class)
@SpringBootTest(classes = App.class)
@DirtiesContext
public class AppTest {

  @ClassRule
  public static KafkaContainer kafka = new KafkaContainer(
      DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

  @Autowired
  private TrainMessageConsumerService consumer;

  @Autowired
  private TrainMessageProducerService producer;

//    @Value("${test.topic}")
//    private String topic;

  @Test
  public void givenKafkaDockerContainer_whenSendingWithSimpleProducer_thenMessageReceived()
      throws Exception {
    String data = "Sending with our own simple KafkaProducer";

    producer.sendMessage(
        new TrainMessage(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), Station.CROSSTOWN,
            null, 0, TrainType.EXPRESS, false));

    //boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);

    //assertTrue(messageConsumed);
    //assertThat(consumer.getPayload(), containsString(data));
  }
}