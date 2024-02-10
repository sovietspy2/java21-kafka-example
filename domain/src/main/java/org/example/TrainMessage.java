package org.example;


import java.time.LocalDateTime;
import java.util.UUID;


public record TrainMessage(UUID id, Station station, LocalDateTime time, Integer passengerCount,
                           TrainType type, Boolean hasError) {

}
