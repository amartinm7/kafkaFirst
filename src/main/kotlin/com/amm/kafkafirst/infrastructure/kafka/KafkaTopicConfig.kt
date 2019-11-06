package com.amm.kafkafirst.infrastructure.kafka

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

import java.util.HashMap

@Configuration
class KafkaTopicConfig {

    private val logger = LoggerFactory.getLogger(KafkaTopicConfig::class.java)

    @Value(value = "\${kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Value(value = "\${kafka.topic.name}")
    private lateinit var topicName: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        logger.info(">>>my bootstrapAddress {}, {}", bootstrapAddress, topicName)
        val configs = HashMap<String, Any>()
        configs[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        return KafkaAdmin(configs)
    }

    @Bean
    fun topic1(): NewTopic {
        logger.info(">>>my bootstrapAddress {}, {}", bootstrapAddress, topicName)
        return NewTopic(topicName, 1, 1.toShort())
    }
}
