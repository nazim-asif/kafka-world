package com.example.kafkapublisher.controller;

import com.example.kafkapublisher.Dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Nazim Uddin Asif
 * @version: 1.0
 */
@RestController
public class KafkaPublisherController {
    @Autowired
    private KafkaTemplate<String, Object> template;

    private String topic = "first_topic";

    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name) {
        template.send(topic, "Hi " + name + " Welcome to Kafka");
        return "Data published";
    }

    @GetMapping("/publishJson")
    public String publishMessage() {
        User user = new User("2532", "User88", new String[] { "123", "Asif", "house 90" });
        template.send(topic, user);
        return "Json Data published";
    }
}
