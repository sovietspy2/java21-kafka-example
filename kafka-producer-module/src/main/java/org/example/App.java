package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
        trainMessageService.sendMessage("Hello, Kafka!");
    }
}