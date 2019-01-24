package com.amm.kafkafirst.application;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import com.amm.kafkafirst.infrastructure.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        topics = ConsumerServiceTest.MY_TOPIC)
public class ConsumerServiceTest {

    static final String MY_TOPIC = "myTopic.t";

    @Autowired
    private ConsumerService receiver;

    @Autowired
    private ProducerService sender;

    @Test
    public void testReceive() throws Exception {
        final Message message = new Message(200, "Hello World " + LocalDate.now());
        sender.sendMessage(message);

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}
