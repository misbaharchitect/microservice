package com.edu.producerapi;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KafkaProducerApi {

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        for(int i = 1; i <= 10; i++) {
        System.out.println("puplishing message");
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("abc", "key-"+i, "value"+i);
            producer.send(producerRecord);
            TimeUnit.SECONDS.sleep(3);
        }

        producer.close();
    }

}
