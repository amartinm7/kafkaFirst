package com.amm.kafkafirst.infrastructure.kafka.producer

import com.amm.kafkafirst.infrastructure.kafka.Message
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

import java.util.HashMap

@Configuration
class KafkaProducerConfig {

    private val logger = LoggerFactory.getLogger(KafkaProducerConfig::class.java)

    @Value(value = "\${kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Message> {
        logger.info(">>>my bootstrapAddress {}", bootstrapAddress)
        val configProps = HashMap<String, Any>()
        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Message> {
        return KafkaTemplate(producerFactory())
    }
}
