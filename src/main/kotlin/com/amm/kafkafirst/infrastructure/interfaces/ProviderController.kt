package com.amm.kafkafirst.infrastructure.interfaces;

import com.amm.kafkafirst.infrastructure.services.ProducerService;
import com.amm.kafkafirst.infrastructure.kafka.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    private ProducerService producerService;

    public ProviderController(@Autowired ProducerService producerService){
        this.producerService = producerService;
    }

    @GetMapping("/provider")
    public ResponseEntity<HttpStatus> index() {
        final Message message = new Message(200, "Hello World " + LocalDate.now());
        producerService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}