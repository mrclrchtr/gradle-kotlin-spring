plugins {
    id("java-conventions")

    // Classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically
    // opened
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    kotlin("plugin.spring")

    // The plugin specifies @Entity, @Embeddable, and @MappedSuperclass no-arg annotations automatically.
    // https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    kotlin("plugin.jpa")

    // Allows to package executable jar or war archives, run Spring Boot applications, and use the dependency management
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
    id("org.springframework.boot")

    // This plugin enables the spring dependency management
    id("io.spring.dependency-management")
}

springBoot {
    // Creates META-INF/build-info.properties for Spring Boot Actuator
    buildInfo {
        // Excludes time from build-info in favor of build cache
        // See https://docs.spring.io/spring-boot/gradle-plugin/integrating-with-actuator.html
        excludes.set(setOf("time"))
    }
}

dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

dependencyManagement {
    configurations.matching { it.name == "detekt" }.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                @Suppress("UnstableApiUsage")
                useVersion(io.gitlab.arturbosch.detekt.getSupportedKotlinVersion())
            }
        }
    }
}
