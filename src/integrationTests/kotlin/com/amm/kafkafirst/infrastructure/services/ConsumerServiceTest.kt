package com.amm.kafkafirst.infrastructure.services

import com.amm.kafkafirst.infrastructure.kafka.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.util.concurrent.TimeUnit

@RunWith(SpringRunner::class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = ["myTopic.t"])
class ConsumerServiceTest {

    private val logger = LoggerFactory.getLogger(ConsumerServiceTest::class.java)

    @Autowired
    private lateinit var receiver: ConsumerService

    @Autowired
    private lateinit var sender: ProducerService

    @Test
    @Throws(Exception::class)
    fun testReceive() {
        val message = Message(200, "Hello World " + LocalDate.now())
        sender.sendMessage(message)

        receiver.latch.await(3000, TimeUnit.MILLISECONDS)
        logger.info("messages {}", receiver.getMessages().toString())
        assertThat(receiver.latch.count).isEqualTo(0)
    }
}
