package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;


public class Javaproducer {

    private static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        System.out.println("started");
        for (int i = 0; i < 10; i++) {
          //  LocalDate date = LocalDate.now().plusDays(i); // Generate dates in sequence
            //String key = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String value = generateRandomString(5); // Generate a random string of length 10
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "02-11-2023", value);
            producer.send(record);
        }

        producer.flush();
        System.out.println("flushed");
        producer.close();
    }
}

