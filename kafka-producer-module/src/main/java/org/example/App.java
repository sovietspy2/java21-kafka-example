package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App
{

    @Autowired
    private TrainMessageService trainMessageService;


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Scheduled(fixedRate = 5000)
    public void process() {
        trainMessageService.sendMessage(generateRandomTrainMessage());
        trainMessageService.sendSimpleMessage();
    }

    private TrainMessage generateRandomTrainMessage() {
        return new TrainMessage(
                UUID.randomUUID(),
                Station.values()[new Random().nextInt(Station.values().length)],
                LocalDateTime.now(),
                new Random().nextInt(100),
                TrainType.values()[(new Random().nextInt(TrainType.values().length))],
                new Random().nextBoolean()
        );
    }
}