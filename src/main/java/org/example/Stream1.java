package org.example;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.*;


import java.util.Properties;

public class Stream1 {

    public static void main(String[] args) {
        Properties streamProps = new Properties();
        streamProps.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams1");
        streamProps.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");


        // Build the Kafka Streams topology
        final Serde<String> stringSerde = Serdes.String();
        StreamsBuilder builder = new StreamsBuilder();

        final KStream<String, String> inputStream = builder.stream("first_topic", Consumed.with(stringSerde, stringSerde));
        final KStream<String, String> outstream = inputStream.map((key, value) -> new KeyValue<>(key, value.toUpperCase()));
        outstream.to("second_topic", Produced.with(Serdes.String(), Serdes.String()));


        KStream<String, String> printStream = builder.stream("second_topic");

        printStream.foreach((key, value) -> {
            System.out.println("Received message - Key: " + key + ", Value: " + value);
        });

        Topology topology = builder.build();
        KafkaStreams streams = new KafkaStreams(topology, streamProps);

        // Start the Kafka Streams application
        streams.start();

        // Add shutdown hook to gracefully close the application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }

}
