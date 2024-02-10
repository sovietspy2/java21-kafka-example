package org.example;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Profile("scheduling")
public class SchedulingService {

  @Autowired
  private TrainMessageProducerService trainMessageProducerService;

  @Scheduled(fixedRate = 5000)
  public void process() {
    trainMessageProducerService.sendMessage(generateRandomTrainMessage());
    trainMessageProducerService.sendSimpleMessage();
  }

  private TrainMessage generateRandomTrainMessage() {
    return new TrainMessage(UUID.randomUUID(),
        Station.values()[new Random().nextInt(Station.values().length)], LocalDateTime.now(),
        new Random().nextInt(100),
        TrainType.values()[(new Random().nextInt(TrainType.values().length))],
        new Random().nextBoolean());
  }
}
