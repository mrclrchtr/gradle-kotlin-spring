package de.mrclrchtr.education.message.factory

import com.thedeanda.lorem.LoremIpsum
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.atomic.AtomicLong
import kotlin.collections.ArrayList

@Service
class MessageService {

    val counter = AtomicLong()
    val random = Random()
    val loremIpsum = LoremIpsum.getInstance()!!
    val messageRepository = HashMap<String, ArrayList<Message>>()

    @SuppressWarnings("UnusedPrivateMember", "MagicNumber")
    fun getMessagesOfUser(username: String): ArrayList<Message> {
        return messageRepository.getOrElse(username) {
            val messages = ArrayList<Message>()
            for (index in 1..random.nextInt(10) + 1) {
                messages.add(Message(counter.get(), loremIpsum.name, loremIpsum.getParagraphs(1, 3)))
            }
            messageRepository[username] = messages
            return messages
        }
    }
}
