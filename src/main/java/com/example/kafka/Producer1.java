package com.example.kafka;

//import org.apache.cassandra.streaming.StreamOut;
//import com.sun.org.apache.xpath.internal.operations.String;
//import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


public class Producer1 {

    public static void main(String[] args) throws InterruptedException {
//        String hostname = "165.232.185.21";
//        String username = "root";
//        String password = "hs9***yUVrEMa";

        //final Logger LOGGER = (Logger) LoggerFactory.getLogger(Producer1.class);

        Properties prop = new Properties();

        //String Bootstrap_server = System.getenv("BOOTSTRAP_SERVER");
        String Client = System.getenv("Properties_file");
        try {
            FileInputStream input = new FileInputStream(Client);
            prop.load(input);
            input.close();
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"pkc-lzvrd.us-west4.gcp.confluent.cloud:9092");
        //prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,Bootstrap_server);
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
//        prop.setProperty("schema.registry.url", "https://psrc-q8qx7.us-central1.gcp.confluent.cloud");
//        prop.setProperty("basic.auth.credentials.source", "USER_INFO");
//        prop.setProperty("basic.auth.user.info" , "GSIJW7LHSPJBEEM4:CNQGSDeEnCoq5fXHVP1QChwuqBQ0moT1E3MjAABkSc4f3l3AE5l5RD26H+DmpRQY");
       // prop.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"GSIJW7LHSPJBEEM4\" password=\"CNQGSDeEnCoq5fXHVP1QChwuqBQ0moT1E3MjAABkSc4f3l3AE5l5RD26H+DmpRQY\";");
        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);
//        final KafkaProducer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(prop);
//
//
//        Schema schema = new org.apache.avro.Schema.Parser("schema1");
//
//        GenericRecord avrorecord = new GenericData.Record(schema);
          System.out.println("started");
//
//        avrorecord.put("my_field", 44);
//        avrorecord.put("myfield2", 88);
//        avrorecord.put("my_field3", "Hello, Avro!");
        for (int i=1;i<=50;i++){
        ProducerRecord<String, String> record = new ProducerRecord<>("test" , "key2" , Integer.toString(i));
            producer.send(record);
            Thread.sleep(1000 * 60);
        }

//        ProducerRecord<String, GenericRecord> record = new ProducerRecord<String, GenericRecord>("test", "key2", avrorecord);
//        producer.send((ProducerRecord<String, GenericRecord>) avrorecord);
//        System.out.println("hello");
//        //producer.flush();



//        Producer<String, Customer> producer = new KafkaProducer<String, Customer>(prop);
//        String topic = "test";
//
//        Customer customer = Customer.newBuilder()
//                .setAge(34)
//                .setAutomatedEmail(false)
//                .setFirstName("son")
//                .setLastName("goku")
//                .setHeight(178f)
//                .setWeight(75f)
//                .build();


//        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<String, Customer>(topic, customer);
//        System.out.println(customer);
       // producer.send(record);
        producer.flush();
        System.out.println("flushed");
        producer.close();

    }
    }

