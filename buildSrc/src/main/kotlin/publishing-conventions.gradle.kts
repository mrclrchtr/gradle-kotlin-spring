import de.mrclrchtr.education.gradle.extension.fromComponent
import de.mrclrchtr.education.gradle.extension.getGitLabToken
import de.mrclrchtr.education.gradle.extension.gitLab

/*
*
* In theory you can use this convention to publish a jar file to a maven repository.
* In case of Spring boot, this will fail because of the bootJar with the error message:
*
* Execution failed for task ':message-dashboard:publishMavenPublicationToMavenLocal'.
* > Failed to publish publication 'maven' to repository 'mavenLocal'
*   > Artifact message-dashboard-DEVELOPMENT-SNAPSHOT.jar wasn't produced by this build.
*
* To fix it, you have to configure the plugin as described here:
*
* https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/htmlsingle/#publishing-your-application-maven
*
*/

plugins {
    id("java-conventions")
    id("dokka-conventions")
    `maven-publish`
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
        fromComponent(components["java"], javadocJar)
    }

    repositories {
        gitLab(getGitLabToken(project))
    }
}
