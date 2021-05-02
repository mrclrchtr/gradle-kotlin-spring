plugins {
    id("java-conventions")

    // Classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically opened
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    kotlin("plugin.spring")

    // Allows to package executable jar or war archives, run Spring Boot applications, and use the dependency management
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
    id("org.springframework.boot")
}

println("Enabling Kotlin Spring plugin in project ${project.name}...")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")

println("Enabling Spring Boot plugin in project ${project.name}...")
apply(plugin = "org.springframework.boot")

println("Enabling Spring Boot Dependency Management in project ${project.name}...")
apply(plugin = "io.spring.dependency-management")

springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo()
}
