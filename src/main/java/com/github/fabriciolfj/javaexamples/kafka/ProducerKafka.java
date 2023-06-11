package com.github.fabriciolfj.javaexamples.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import static com.github.fabriciolfj.javaexamples.constants.KAFKAConstants.KAFKA_BOOTSTRAP;

public class ProducerKafka {

    private static KafkaProducer<String, String> producer;

    public static KafkaProducer<String, String> getProducer() {
        if (producer == null) {
            var config = new ProducerKafka();
            producer = config.producer();
        }

        return producer;
    }

    private KafkaProducer<String, String> producer() {
        final Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return new KafkaProducer<>(producerProperties);
    }
}
