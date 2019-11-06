package com.amm.kafkafirst.infrastructure.kafka.consumer

import com.amm.kafkafirst.infrastructure.kafka.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

import java.util.HashMap

@EnableKafka
@Configuration
class KafkaConsumerConfig {

    private val logger = LoggerFactory.getLogger(KafkaConsumerConfig::class.java)

    @Value(value = "\${kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Value(value = "\${kafka.group.id}")
    private lateinit var groupId: String

    fun consumerFactory(): ConsumerFactory<String, Message> {
        logger.info(">>>my bootstrapAddress {}, {}", bootstrapAddress, groupId)
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        props[JsonDeserializer.TRUSTED_PACKAGES] = "*"

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    @Bean
    fun filterKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Message> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
        factory.consumerFactory = consumerFactory()
        factory.setRecordFilterStrategy { record -> record.value().message!!.contains("kk") } //skip items when the predicate is true
        return factory
    }
}
