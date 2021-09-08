import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    id("java-conventions")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED)
        exceptionFormat = FULL
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
