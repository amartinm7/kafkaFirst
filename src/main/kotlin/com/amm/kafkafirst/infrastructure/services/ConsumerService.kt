package com.amm.kafkafirst.infrastructure.services

import com.amm.kafkafirst.infrastructure.kafka.Message
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

import java.util.ArrayList
import java.util.concurrent.CountDownLatch

@Service
class ConsumerService {

    private val logger = LoggerFactory.getLogger(ConsumerService::class.java)
    private val messages = ArrayList<Message>()
    val latch: CountDownLatch = CountDownLatch(1)

    @KafkaListener(
        topics = ["\${kafka.topic.name}"],
        groupId = "\${kafka.group.id}",
        containerFactory = "filterKafkaListenerContainerFactory"
    )
    fun listen(message: Message) {
        logger.info(">>>Received message in group foo: $message")
        messages.add(message)
        latch.countDown()
    }

    fun getMessages(): List<Message> {
        return messages
    }
}
