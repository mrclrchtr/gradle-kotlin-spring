@file:Suppress("UnstableApiUsage")

import de.mrclrchtr.education.gradle.constant.JDK_VERSION
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("java-conventions")

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm")

    // A tool to detect kotlin problems. It's nice, give it a try!
    id("io.gitlab.arturbosch.detekt")
}

tasks.compileKotlin {
    println("Configuring KotlinCompile  $name in project ${project.name}...")
    kotlinOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget = JDK_VERSION
        languageVersion = "1.6"
        apiVersion = "1.6"
    }
}

tasks.compileTestKotlin {
    println("Configuring KotlinTestCompile  $name in project ${project.name}...")
    kotlinOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget = JDK_VERSION
        languageVersion = "1.6"
        apiVersion = "1.6"
    }
}

detekt {
    ignoreFailures = false
    buildUponDefaultConfig = true
    config = files("$rootDir/detekt.yml")
    parallel = true
}

dependencies {
    // unfortunately I have not found a way to reuse the version from the build.gradle.kts in buildSrc
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
}

tasks.withType<Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = JDK_VERSION
}
