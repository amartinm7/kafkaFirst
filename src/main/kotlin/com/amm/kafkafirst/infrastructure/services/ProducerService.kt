package com.amm.kafkafirst.infrastructure.services

import com.amm.kafkafirst.infrastructure.kafka.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.lang.NonNull
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class ProducerService(
    @param:Autowired private val kafkaTemplate: KafkaTemplate<String, Message>
) {

    private val logger = LoggerFactory.getLogger(ProducerService::class.java)

    @Value(value = "\${kafka.topic.name}")
    private val topicName: String? = null

    fun sendMessage(message: Message) {
        val future = kafkaTemplate.send(topicName!!, message)
        future.addCallback(object : ListenableFutureCallback<SendResult<String, Message>> {
            override fun onSuccess(@NonNull result: SendResult<String, Message>?) {
                logger.info(
                    "Sent message=[" + message +
                        "] with offset=[" + result!!.recordMetadata.offset() + "]"
                )
            }

            override fun onFailure(ex: Throwable) {
                logger.info(
                    "Unable to send message=["
                        + message + "] due to : " + ex.message
                )
            }
        })
    }
}
