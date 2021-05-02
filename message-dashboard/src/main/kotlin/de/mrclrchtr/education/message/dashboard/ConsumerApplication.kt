package de.mrclrchtr.education.message.dashboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerApplication

fun main(vararg args: String) {
    runApplication<ConsumerApplication>(*args)
}
