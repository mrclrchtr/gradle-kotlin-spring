package de.mrclrchtr.education.gradle.extension

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.component.SoftwareComponent
import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.authentication.http.HttpHeaderAuthentication
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.credentials
import org.gradle.kotlin.dsl.provideDelegate
import java.net.URI

// Setup your repository and tokens. This example is working for GitLab.
fun RepositoryHandler.gitLab(gitLabPrivateToken: String?): MavenArtifactRepository {
    return maven {
        url = URI("https://gitlab.com/api/v4/projects/xxxxx/packages/maven")
        name = "gradle-kotlin-spring"
        credentials(HttpHeaderCredentials::class) {
            if (!System.getenv("CI_JOB_TOKEN").isNullOrEmpty()) {
                name = "Job-Token"
                value = System.getenv("CI_JOB_TOKEN")
            } else {
                if (gitLabPrivateToken.isNullOrEmpty()) {
                    throw IllegalStateException("Neither CI_JOB_TOKEN nor gitLabPrivateToken are set. At least one variable must be set.")
                }
                name = "Private-Token"
                value = gitLabPrivateToken
            }
        }
        authentication {
            create<HttpHeaderAuthentication>("header")
        }
    }
}

fun PublicationContainer.fromComponent(
    fromComponent: SoftwareComponent? = null,
    javadocJar: Jar? = null
): MavenPublication {
    return create<MavenPublication>("maven") {
        pom {
            url.set("https://gitlab.com/xxx/yyy/zzz")
            scm {
                connection.set("scm:git:git://gitlab.com/xxx/yyy/zzz.git")
                developerConnection.set("scm:git:git@gitlab.com:xxx/yyy/zzz.git")
                url.set("https://gitlab.com/xxx/yyy/zzz")
            }
        }
        // Adding the main artifact and sources if available
        fromComponent?.also { from(fromComponent) }
        // Adding the documentation artifact if available
        javadocJar?.also { artifact(javadocJar) }
    }
}

fun getGitLabToken(project: Project): String {
    return if (!System.getenv("CI_JOB_TOKEN").isNullOrEmpty()) {
        System.getenv("CI_JOB_TOKEN")
    } else {
        val gitLabPrivateToken: String? by project
        gitLabPrivateToken?.also { return it }
            ?: throw IllegalStateException("Neither CI_JOB_TOKEN nor gitLabPrivateToken are set. At least one variable must be set.")
    }
}
