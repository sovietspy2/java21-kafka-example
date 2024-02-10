package org.example;


import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class KafkaTestContainersConfiguration {

//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        // Configure deserializers
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> notTheDefaultKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, TrainMessage> trainConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        // Configure deserializers
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "org.example"); // Set trusted packages if needed
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, TrainMessage> kafkaTrainMessageListenerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, TrainMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(trainConsumerFactory());
//        return factory;
//    }
//
//    private String bootstrapAddress = "localhost:9092";
//
//    //////////////////
//
//    Map<String, Object> getProducerProps(Class<?> messageSerializer) {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, messageSerializer);
//        return configProps;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> simpleKafkaProducerFactory() {
//        Map<String, Object> configProps = getProducerProps(StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//
//    @Bean
//    public ProducerFactory<String, TrainMessage> trainMessageProducerFactory() {
//        Map<String, Object> configProps = getProducerProps(JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, TrainMessage> trainMessageKafkaTemplate() {
//        return new KafkaTemplate<>(trainMessageProducerFactory());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> simpleKafkaTemplate() {
//        return new KafkaTemplate<>(simpleKafkaProducerFactory());
//    }
}
