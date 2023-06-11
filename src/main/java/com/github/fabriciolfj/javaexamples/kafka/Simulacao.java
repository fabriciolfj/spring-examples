package com.github.fabriciolfj.javaexamples.kafka;

import com.github.fabriciolfj.javaexamples.constants.KAFKAConstants;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.concurrent.ExecutionException;

public class Simulacao {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final String TOPIC = "topic-example";
        int messagesTopic = 100;

        final var producer = ProducerKafka.getProducer();

        for (int i = 0; i < messagesTopic; i++) {
            producer.send(new ProducerRecord<>(TOPIC, null, KAFKAConstants.KEY, String.valueOf(i))).get();
        }
    }
}
