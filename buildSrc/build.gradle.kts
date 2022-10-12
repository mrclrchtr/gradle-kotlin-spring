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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.4")
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.7.20")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
}
