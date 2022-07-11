plugins {
    id("kotlin-conventions")
    id("testing-conventions")
    id("dokka-conventions")
//  id("publishing-conventions") // If everything was configured correctly, you could use it to publish the artifacts. But it is not working with Spring as I thought.
    id("spring-conventions")
}

val loremVersion: String by rootProject.extra

dependencies {
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation(libs.bundles.lorem)

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
