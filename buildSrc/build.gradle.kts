plugins {
    // Support convention plugins written in Kotlin. Convention plugins are build scripts in 'src/main' that automatically become available as plugins in the main build.
    `kotlin-dsl`
}

repositories {
    // Use the plugin portal to apply community plugins in convention plugins.
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.2")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.7.10")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
}
