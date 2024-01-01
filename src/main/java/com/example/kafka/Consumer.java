package com.example.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
//import java.util.logging.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Consumer {

    public static void main(String[] args) throws InterruptedException {
//        String classpath = System.getProperty("java.class.path");
//        System.out.println("Classpath: " + classpath);
       final Logger logger = (Logger) getLogger(Consumer.class);
        final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
       // final String bootstrapServers = "pkc-lzvrd.us-west4.gcp.confluent.cloud:9092";
       // final String bootstrapServers = "pkc-lzvrd.us-west4.gcp.confluent.cloud:9092";
        //final String ConsumerGroupID = " consumer-group10";
        //String ConsumerGroupID = System.getenv("CONSUMER_GROUP_ID");
        String Client = System.getenv("Properties_file");
       // String CLASSPATH = System.getenv("CLASSPATH");

        Properties p = new Properties();
        try {
            FileInputStream input = new FileInputStream(Client);
            p.load(input);
            input.close();
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //p.setProperty(ConsumerConfig.GROUP_ID_CONFIG , "ConsumerGroup");
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        System.out.println(p.toString());
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(p);

        consumer.subscribe(Arrays.asList("test"));

        while (true) {
           ConsumerRecords<String, String> records =  consumer.poll(Duration.ofMillis(1000));
           for (ConsumerRecord record: records) {
               LOGGER.info("Received new record: \n" +
                       "Key: " + record.key() + ", " +
                       "Value: " + record.value() + ", " +
                       "Topic: " + record.topic() + ", " +
                       "Partition: " + record.partition() + ", " +
                       "Offset: " + record.offset() + "\n");
               Thread.sleep(1000 * 120);
               consumer.commitSync();
           }

        }
       // interceptor.classes=io.confluent.connect.replicator.offsets.ConsumerTimestampsInterceptor;
    }

}
