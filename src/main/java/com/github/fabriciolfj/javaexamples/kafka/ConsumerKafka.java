package com.github.fabriciolfj.javaexamples.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Properties;
import static com.github.fabriciolfj.javaexamples.constants.KAFKAConstants.KAFKA_BOOTSTRAP;

public class ConsumerKafka {

    private static KafkaConsumer<String, String> consumer;

    public static KafkaConsumer<String, String> getConsumer() {
        if (consumer == null) {
            var config = new ConsumerKafka();
            consumer = config.consumer();
        }

        return consumer;
    }

    private KafkaConsumer<String, String> consumer() {
        final Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerGroup1");

        return new KafkaConsumer<>(consumerProperties);
    }
}
