package de.mrclrchtr.education.message.dashboard

import j2html.TagCreator.body
import j2html.TagCreator.div
import j2html.TagCreator.each
import j2html.TagCreator.h1
import j2html.TagCreator.h2
import j2html.TagCreator.head
import j2html.TagCreator.hr
import j2html.TagCreator.html
import j2html.TagCreator.p
import j2html.TagCreator.title
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * This class provides the endpoints that deliver the dashboard.
 *
 * @property dashboardService the dashboard service that delivers the messages for the user.
 * @constructor Creates the DashboardController.
 */
@RestController
class DashboardController(@Autowired val dashboardService: DashboardService) {

    /**
     * Creates a dashboard for a specific [username].
     * @param username the username of the user
     * @return the html of the dashboard
     */
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
