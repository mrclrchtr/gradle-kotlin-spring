package de.mrclrchtr.education.message.dashboard

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ConsumerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConsumerApplication::class.java, *args)
}
