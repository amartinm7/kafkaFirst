package com.amm.kafkafirst.interfaces;

import com.amm.kafkafirst.application.ProducerService;
import com.amm.kafkafirst.infrastructure.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
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