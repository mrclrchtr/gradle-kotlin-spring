package de.mrclrchtr.education.message.factory

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController @Autowired constructor(
    private val messageService: MessageService
) {
    @GetMapping("/messages/{username}")
    fun getMessagesOfUser(@PathVariable("username") username: String) = messageService.getMessagesOfUser(username)
}
