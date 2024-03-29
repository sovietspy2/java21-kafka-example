package org.example;

import java.util.UUID;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

@RunWith(SpringRunner.class)
@Import(AppTest.KafkaTestContainersConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest(classes = App.class)
@DirtiesContext
public class AppTest {

  @ClassRule
  public static KafkaContainer kafka = new KafkaContainer(
      DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

  @Autowired
  private TrainMessageProducerService service;

  @Test
  public void givenKafkaDockerContainer_whenSendingWithDefaultTemplate_thenMessageReceived()
      throws Exception {
    service.sendMessage(
        new TrainMessage(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), Station.CROSSTOWN,
            null, 0, TrainType.EXPRESS, false));
  }

  @TestConfiguration
  static class KafkaTestContainersConfiguration extends KafkaProducerConfig {

  }

}