import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.21"
    val springVersion = "2.4.1"
    val springDependencyManagementVersion = "1.0.10.RELEASE"

    // IntelliJ
    idea

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm") version kotlinVersion apply false

    // Classes annotated with @Configuration, @Controller, @RestController, @Service or @Repository are automatically opened
    // https://kotlinlang.org/docs/reference/compiler-plugins.html#spring-support
    kotlin("plugin.spring") version kotlinVersion apply false

    // Allows to package executable jar or war archives, run Spring Boot applications, and use the dependency management
    // https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/html/
    id("org.springframework.boot") version springVersion apply false

    // A Gradle plugin that provides Maven-like dependency management and exclusions
    // https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/
    id("io.spring.dependency-management") version springDependencyManagementVersion

    id("com.github.ben-manes.versions") version "0.36.0" // For dependency version upgrades "gradle dependencyUpdates -Drevision=release"
}

allprojects {
    group = "de.mrclrchtr.education"
    version = "1.1"

    repositories {
        jcenter()
    }
}

subprojects {

    println("Enabling Spring Boot plugin in project ${project.name}...")
    apply(plugin = "org.springframework.boot")

    println("Enabling Kotlin Spring plugin in project ${project.name}...")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    tasks.withType<KotlinCompile> {
        println("Configuring KotlinCompile  $name in project ${project.name}...")
        kotlinOptions {
            languageVersion = "1.4"
            apiVersion = "1.4"
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    println("Enabling Spring Boot Dependency Management in project ${project.name}...")
    apply(plugin = "io.spring.dependency-management")
    configure<DependencyManagementExtension> {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
