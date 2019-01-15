plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-actuator")

    compile("org.springframework.boot:spring-boot-devtools")
}
