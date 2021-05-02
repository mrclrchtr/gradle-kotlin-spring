@file:Suppress("UnstableApiUsage")

import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("java-conventions")

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm")

    // A tool to detect kotlin problems. It's nice, give it a try!
    id("io.gitlab.arturbosch.detekt")
}

val jvmTargetVersion: String by rootProject.extra
val detektVersion: String by rootProject.extra

tasks.compileKotlin {
    println("Configuring KotlinCompile  $name in project ${project.name}...")
    kotlinOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jdkHome = javaToolchains.compilerFor(java.toolchain).get().metadata.installationPath.asFile.absolutePath
        jvmTarget = jvmTargetVersion
        languageVersion = "1.4"
        apiVersion = "1.4"
    }
}

tasks.compileTestKotlin {
    println("Configuring KotlinTestCompile  $name in project ${project.name}...")
    kotlinOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
        jdkHome = javaToolchains.compilerFor(java.toolchain).get().metadata.installationPath.asFile.absolutePath
        jvmTarget = jvmTargetVersion
        languageVersion = "1.4"
        apiVersion = "1.4"
    }
}

detekt {
    ignoreFailures = false
    buildUponDefaultConfig = true
    config = files("$rootDir/detekt.yml")
    reports {
        xml.enabled = false
        html.enabled = false
        txt.enabled = false
    }
    parallel = true
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

tasks.withType<Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    this.jvmTarget = jvmTargetVersion
}

repositories {
    // Fix for https://github.com/detekt/detekt/issues/3712
    // TODO: https://github.com/mrclrchtr/gradle-kotlin-spring/issues/9 Remove it when the issue was closed
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}
