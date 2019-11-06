package com.amm.kafkafirst.infrastructure.interfaces;

import com.amm.kafkafirst.infrastructure.services.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    private ConsumerService consumerService;

    public ConsumerController(@Autowired ConsumerService consumerService){
        this.consumerService = consumerService;
    }

    @GetMapping("/consumer")
    public ResponseEntity<HttpStatus> index() {
        logger.info(">>> {} ", consumerService.getMessages().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}