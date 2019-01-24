package com.amm.kafkafirst.interfaces;

import com.amm.kafkafirst.application.ConsumerService;
import com.amm.kafkafirst.application.ProducerService;
import com.amm.kafkafirst.infrastructure.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    private ConsumerService consumerService;

    public ConsumerController(@Autowired ConsumerService consumerService){
        this.consumerService = consumerService;
    }

    @GetMapping("/consumer")
    public ResponseEntity<HttpStatus> index() {
        // Message message = new Message(200, "Hello World");
        logger.info(">>> {} ", consumerService.getMessages().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}