package de.mrclrchtr.education.message.dashboard

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class ConsumerApplicationTest {

    @Test
    @DisplayName("Check if context loads")
    fun contextLoads() {
        // Check if context loads
    }
}
