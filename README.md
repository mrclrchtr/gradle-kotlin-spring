# Gradle, Kotlin, Spring Skeleton
[![Run Gradle on PRs](https://github.com/mrclrchtr/gradle-kotlin-spring/actions/workflows/gradle-build-pr.yml/badge.svg)](https://github.com/mrclrchtr/gradle-kotlin-spring/actions/workflows/gradle-build-pr.yml)

Do you already know how to set up a Gradle multi-project / multi-module with Kotlin DSL and Spring Dependency
Management?

I have set up an example here, in which I have abstracted the most important logical components and packed them into
individual buildSrc modules. This makes the build very modular and also very clear. What do you think?

It has the following details:
- Java 17
# renovate: datasource=gradle-version depName=gradle
- Gradle 8.4
  - buildSrc
  - Kotlin DSL
  - Version Catalog (libs.versions.toml)
  - Gradle Build Scan enabled in CI (Attention! Terms of Service are accepted by the environment variable `BUILD_SCAN_TOS_ACCEPTED` and the scan enabled by the environment variable `CI`)
# renovate: datasource=kotlin-version depName=kotlin
- Kotlin 1.9.20
# renovate: datasource=github-tags depName=spring-boot
- Spring Boot 3.1.5
# renovate: datasource=github-tags depName=detekt
- Detekt 1.23.3
# renovate: datasource=github-tags depName=dokka
- Dokka 1.9.10
- Spring Dependency-Management

Additionally, I added a POC how Maven Publishing could work. In another project, I got it to work like this without
Spring boot on Gitlab. Unfortunately it doesn't work with the spring boot plugin in this example yet. But I think it can
help others to reach their goals.

If there are any questions or suggestions for improvement, issues, discussions and PRs are welcome.

## Run it!

The factory:

    gradle :message-factory:bootRun

The Dashboard:

    gradle :message-dashboard:bootRun

And now visit <http://localhost:8080/dashboard/Mr.Bean>

You can enter every name in place of "Mr.Bean". 
