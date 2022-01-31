package de.mrclrchtr.education.message.dashboard

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

private val logger = KotlinLogging.logger {}

@Component
class MessageClient(
    templateBuilder: RestTemplateBuilder,
    @Value("\${mrclrchtr.message.factory.protocol}") val protocol: String,
    @Value("\${mrclrchtr.message.factory.host}") val host: String,
    @Value("\${mrclrchtr.message.factory.port}") val port: Int
) {

    private final var uriComponentsBuilder = UriComponentsBuilder.newInstance()

    private val restTemplate = templateBuilder.rootUri(
        uriComponentsBuilder
            .scheme(protocol)
            .host(host)
            .port(port)
            .build()
            .toUriString()
    ).build()

    fun getMessagesOfUser(username: String): List<Message> {
        // I would like to use this.. but its not possible because it returns List<LinkedHashMap>>
        // return restTemplate.getForObject("/messages/$username") ?: ArrayList()
        val response = restTemplate.exchange(
            "/messages/$username", HttpMethod.GET, null,
            object : ParameterizedTypeReference<List<Message>>() {}
        )
        return response.body ?: ArrayList()
    }
}
