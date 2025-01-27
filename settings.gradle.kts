plugins {
    id("com.gradle.enterprise").version("3.15.1")
}

rootProject.name = "gradle-kotlin-spring"
include(":message-factory")
include(":message-dashboard")

if (!System.getenv("BUILD_SCAN").isNullOrEmpty() && !System.getenv("BUILD_SCAN_TOS_ACCEPTED").isNullOrEmpty()) {
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            tag("CI")
        }
    }
}
