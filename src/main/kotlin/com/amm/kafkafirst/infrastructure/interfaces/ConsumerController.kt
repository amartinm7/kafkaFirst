package com.amm.kafkafirst.infrastructure.interfaces

import com.amm.kafkafirst.infrastructure.services.ConsumerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ConsumerController(@param:Autowired private val consumerService: ConsumerService) {

    private val logger = LoggerFactory.getLogger(ConsumerController::class.java)

    @GetMapping("/consumer")
    fun index(): ResponseEntity<HttpStatus> {
        logger.info(">>> {} ", consumerService.getMessages().toString())
        return ResponseEntity(HttpStatus.OK)
    }
}