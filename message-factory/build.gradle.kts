plugins {
    id("kotlin-conventions")
    id("testing-conventions")
    id("publishing-conventions")
    id("spring-conventions")
}

val loremVersion: String by rootProject.extra

dependencies {
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.thedeanda:lorem:$loremVersion")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
