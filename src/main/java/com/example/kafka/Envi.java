package com.example.kafka;

public class Envi {
        public static void main(String[] args) {
            // Set the value for the CONSUMER_GROUP_ID environment variable
            System.setProperty("CONSUMER_GROUP_ID", "consumer-group4");

            // Retrieve and print the value to verify
            String ConsumerGroupID = System.getProperty("CONSUMER_GROUP_ID");
            System.out.println("Consumer_Group_ID: " + ConsumerGroupID);
        }
    }

