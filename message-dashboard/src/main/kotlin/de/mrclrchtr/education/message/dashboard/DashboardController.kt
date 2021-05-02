package de.mrclrchtr.education.message.dashboard

import j2html.TagCreator.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class DashboardController(@Autowired val dashboardService: DashboardService) {

    @GetMapping("/dashboard/{username}")
    fun getDashboardOfUser(@PathVariable("username") username: String): String {
        val messages = dashboardService.getMessagesOfUser(username)
        return createHTML(username, messages)
    }

    fun createHTML(username: String, messages: List<Message>): String {
        return html(
            head(
                title("Message Dashboard")
            ),
            body(
                h1("Message Dashboard for $username"),
                hr(),
                each(
                    messages
                ) { message ->
                    div(
                        h2("From " + message.from),
                        hr(),
                        p(message.content)
                    )
                }
            )
        ).renderFormatted()
    }
}
