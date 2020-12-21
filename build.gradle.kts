import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    kotlin("jvm") version "1.3.71" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.21" apply false
    id("org.springframework.boot") version "2.2.6.RELEASE" apply false
    id("com.github.ben-manes.versions") version "0.28.0" // For dependency version upgrades "gradle dependencyUpdates -Drevision=release"
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
            languageVersion = "1.3"
            apiVersion = "1.3"
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
