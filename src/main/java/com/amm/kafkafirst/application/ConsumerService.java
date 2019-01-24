package com.amm.kafkafirst.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
    private final List<String> messages = new ArrayList<>();
    private CountDownLatch latch;

    public ConsumerService(){
        this.latch = new CountDownLatch(1);
    }

    //@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}", containerFactory = "filterKafkaListenerContainerFactory")
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listen(String message) {
        logger.info("Received message in group foo: " + message);
        messages.add(message);
        latch.countDown();
    }

    public List<String> getMessages() {
        return messages;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
