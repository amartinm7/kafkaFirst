package com.amm.kafkafirst

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication(scanBasePackages = ["com.amm.kafkafirst.infrastructure"])
@EnableSwagger2
class Application

fun main() {
    runApplication<Application>()
}
