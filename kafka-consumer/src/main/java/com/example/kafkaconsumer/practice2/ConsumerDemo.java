package com.example.kafkaconsumer.practice2;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author: Nazim Uddin Asif
 * @version: 1.0
 */
public class ConsumerDemo {
  public static void main(String[] args) {
      String bootStrapService ="localhost:9092";
      String groupId = "my_application";
      String topic = "first_topic";
      Properties properties = new Properties();
      properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapService);
      properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
      properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
      properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
      consumer.subscribe(Arrays.asList(topic));

      while (true){
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

          for (ConsumerRecord record : records) {
               System.out.println("Key: "+ record.key());
               System.out.println("Value: "+ record.value());
               System.out.println("Partition: "+ record.partition());
               System.out.println("Offset: "+ record.offset());
          }
      }
  }
}
