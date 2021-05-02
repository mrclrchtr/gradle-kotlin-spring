package de.mrclrchtr.education.message.dashboard

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DashboardService(@Autowired val messageClient: MessageClient) {
    fun getMessagesOfUser(userName: String): List<Message> {
        return messageClient.getMessagesOfUser(userName)
    }
}
