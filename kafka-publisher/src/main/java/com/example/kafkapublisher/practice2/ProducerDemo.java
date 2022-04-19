package com.example.kafkapublisher.practice2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author: Nazim Uddin Asif
 * @version: 1.0
 */
public class ProducerDemo {
  public static void main(String[] args) {

      String bootStrapService ="localhost:9092";
      Properties properties = new Properties();
      properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapService);
      properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


      KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

      ProducerRecord< String, String> producerRecord = new ProducerRecord<String, String>("first_topic","hello world");

      kafkaProducer.send(producerRecord);

      kafkaProducer.flush();
      kafkaProducer.close();
  }
}
