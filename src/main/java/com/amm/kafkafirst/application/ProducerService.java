package com.amm.kafkafirst.application;

import com.amm.kafkafirst.infrastructure.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public ProducerService(
            @Autowired KafkaTemplate<String, Message> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message){
        ListenableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
            @Override
            public void onSuccess(@NonNull SendResult<String, Message> result) {
                logger.info("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
