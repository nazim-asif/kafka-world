package com.example.kafkaconsumer.controller;

import com.example.kafkaconsumer.Dto.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/**
 * @author: Nazim Uddin Asif
 * @version: 1.0
 */
@RestController
public class KafkaConsumerController {
    List<String> messages = new ArrayList<>();

    List<User> userFromTopic = new ArrayList<>();

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {
        return messages;
    }

    @GetMapping("/consumeJsonMessage")
    public List<User> consumeJsonMessage() {
        return userFromTopic;
    }

    @KafkaListener(groupId = "java-1", topics = "first_topic", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "java-2", topics = "first_topic", containerFactory = "userKafkaListenerContainerFactory")
    public List<User> getJsonMsgFromTopic(User user) {
        userFromTopic.add(user);
        return userFromTopic;
    }
}
