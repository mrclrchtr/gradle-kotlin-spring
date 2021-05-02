plugins {
    idea
    `maven-publish`
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

val jdkVersion by extra("11")
val jvmTargetVersion by extra("11")
val detektVersion by extra("1.16.0") // unfortunately I have not found a way to reuse the version from the build.gradle.kts in buildSrc

// Libs
val j2htmlVersion by extra("1.4.0")
val kotlinLoggingVersion by extra("2.0.6")
val loremVersion by extra("2.1")

// Test
val junitVersion by extra("5.7.1")
