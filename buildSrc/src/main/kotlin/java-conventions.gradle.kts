@file:Suppress("UnstableApiUsage")

plugins {
    `java-library`
}

val jdkVersion: String by rootProject.extra

java {
    // Auto JDK setup
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jdkVersion))
    }
    withSourcesJar()
}

tasks.compileJava {
    // See: https://docs.oracle.com/en/java/javase/12/tools/javac.html
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all", // Enables all recommended warnings.
            "-Werror" // Terminates compilation when warnings occur.
        )
    )
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
            )
        )
    }
}

repositories {
    mavenCentral()
}
