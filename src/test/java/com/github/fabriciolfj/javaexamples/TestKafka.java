package com.github.fabriciolfj.javaexamples;

import com.github.fabriciolfj.javaexamples.constants.KAFKAConstants;
import com.github.fabriciolfj.javaexamples.kafka.ConsumerKafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestKafka {

    @Test
    public void test() {
        int messagesInTopic = 100;
        var consumer = ConsumerKafka.getConsumer();

        TopicPartition partition = new TopicPartition("topic-example", 0);
        List<TopicPartition> partitions = new ArrayList<>();
        partitions.add(partition);
        consumer.assign(partitions);

        int messagesToRetrieve = 10;
        consumer.seekToEnd(partitions);
        long startIndex = consumer.position(partition) - messagesToRetrieve;
        consumer.seek(partition, startIndex);
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMinutes(1));

        var recordsReceived = 0;

        for (ConsumerRecord<String, String> record : records) {
            assertEquals(KAFKAConstants.KEY, record.key());
            assertTrue(Integer.parseInt(record.value()) >= (messagesInTopic - messagesToRetrieve));
            recordsReceived++;
        }

        assertEquals(messagesToRetrieve, recordsReceived);
    }
}
