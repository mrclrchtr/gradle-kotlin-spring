import de.mrclrchtr.education.gradle.extension.fromComponent
import de.mrclrchtr.education.gradle.extension.getGitLabToken
import de.mrclrchtr.education.gradle.extension.gitLab

plugins {
    id("org.jetbrains.dokka")
}

dependencies {
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin")
}

tasks.dokkaJavadoc {
    outputDirectory.set(buildDir.resolve("javadoc"))
}
