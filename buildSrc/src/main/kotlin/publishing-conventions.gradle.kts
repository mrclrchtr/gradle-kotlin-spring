import de.mrclrchtr.education.gradle.extension.xxx
import de.mrclrchtr.education.gradle.extension.gitLab
import de.mrclrchtr.education.gradle.extension.getGitLabToken

plugins {
    id("java-conventions")
    `maven-publish`
    id("org.jetbrains.dokka")
}

dependencies {
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin")
}

tasks.dokkaJavadoc {
    outputDirectory.set(buildDir.resolve("javadoc"))
}

val javadocJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc)
    dependsOn(tasks.dokkaJavadoc)
}

publishing {
    publications {
        xxx(components["java"], javadocJar)
    }

    repositories {
        gitLab(getGitLabToken(project))
    }
}
