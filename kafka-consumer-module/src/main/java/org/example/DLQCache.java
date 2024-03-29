package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class DLQCache {

  private final Map<TrainMessage, Integer> cache = new ConcurrentHashMap<>();

  public Integer get(TrainMessage message) {
    return cache.get(message);
  }

  public void put(TrainMessage message) {
    Integer count = cache.get(message);
    cache.put(message, count == null ? 1 : ++count);
  }

  public void evict(TrainMessage message) {
    cache.remove(message);
  }


}
