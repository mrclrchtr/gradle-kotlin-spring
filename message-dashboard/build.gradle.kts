plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")

    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-actuator")

    implementation("org.springframework.boot", "spring-boot-devtools")

    implementation("com.j2html", "j2html", "1.4.0")

    implementation("io.github.microutils", "kotlin-logging", "2.0.4")

    testImplementation("org.springframework.boot", "spring-boot-starter-test")
}
