package com.example.kafkapublisher.practice2;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author: Nazim Uddin Asif
 * @version: 1.0
 */
public class ProducerDemoConfig {
  public static void main(String[] args) {

      String bootStrapService ="localhost:9092";
      Properties properties = new Properties();
      properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapService);
      properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


      KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);


    for (int i = 0;i < 10; i++) {
        ProducerRecord< String, String> producerRecord = new ProducerRecord<String, String>("first_topic","hello world "+ i);
    kafkaProducer.send(
        producerRecord,
        new Callback() {
          @Override
          public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e == null) {
              System.out.println(" topic: " + recordMetadata.topic());
              System.out.println(" partition: " + recordMetadata.partition());
              System.out.println(" offset: " + recordMetadata.offset());
              System.out.println(" Timestamp: " + recordMetadata.timestamp());
            } else {
              System.out.println(" error: "+ e.getMessage());
            }
          }
        });
    }
      kafkaProducer.flush();
      kafkaProducer.close();
  }

}
