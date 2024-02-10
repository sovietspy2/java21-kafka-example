package org.example;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@Profile("local")
public class KafkaProducerConfig extends KafkaConfig {

  Map<String, Object> getProducerProps(Class<?> messageSerializer) {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, messageSerializer);
    return configProps;
  }

  @Bean
  public ProducerFactory<String, String> simpleKafkaProducerFactory() {
    Map<String, Object> configProps = getProducerProps(StringSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }


  @Bean
  public ProducerFactory<String, TrainMessage> trainMessageProducerFactory() {
    Map<String, Object> configProps = getProducerProps(JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, TrainMessage> trainMessageKafkaTemplate() {
    return new KafkaTemplate<>(trainMessageProducerFactory());
  }

  @Bean
  public KafkaTemplate<String, String> simpleKafkaTemplate() {
    return new KafkaTemplate<>(simpleKafkaProducerFactory());
  }

}