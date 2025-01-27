# Gradle, Kotlin, Spring Skeleton
[![Run Gradle on PRs](https://github.com/mrclrchtr/gradle-kotlin-spring/actions/workflows/gradle-build-pr.yml/badge.svg)](https://github.com/mrclrchtr/gradle-kotlin-spring/actions/workflows/gradle-build-pr.yml)

Do you already know how to set up a Gradle multi-project / multi-module with Kotlin DSL and Spring Dependency
Management?

I have set up an example here, in which I have abstracted the most important logical components and packed them into
individual buildSrc modules. This makes the build very modular and also very clear. What do you think?

It has the following details:
- Java `21`
<!-- renovate: datasource=gradle-version depName=gradle -->
- Gradle `8.4`
  - buildSrc
  - Kotlin DSL
  - Version Catalog (libs.versions.toml)
  - Gradle Build Scan can be enabled in CI (Attention! Terms of Service are accepted by the environment variable `BUILD_SCAN_TOS_ACCEPTED` and the scan can be enabled by the environment variable `BUILD_SCAN`)
<!-- renovate: datasource=maven depName=org.jetbrains.kotlin:kotlin-gradle-plugin -->
- Kotlin `2.1.0`
<!-- renovate: datasource=maven depName=org.springframework.boot:spring-boot-gradle-plugin -->
- Spring Boot `3.4.1`
<!-- renovate: datasource=maven depName=io.gitlab.arturbosch.detekt:detekt-gradle-plugin -->
- Detekt `1.23.7`
<!-- renovate: datasource=maven depName=org.jetbrains.dokka:dokka-gradle-plugin -->
- Dokka `2.0.0`
<!-- formatting comment -->
- Spring Dependency-Management

Additionally, I added a POC how Maven Publishing could work. In another project, I got it to work like this without
Spring boot on Gitlab. Unfortunately, it doesn't work with the spring boot plugin in this example yet. But I think it can
help others to reach their goals.

If there are any questions or suggestions for improvement, issues, discussions and PRs are welcome.

## Run it!

The factory:
```shell
    ./gradlew :message-factory:bootRun
```

The Dashboard:
```shell
    ./gradlew :message-dashboard:bootRun
```

And now visit <http://localhost:8080/dashboard/Mr.Bean>

You can enter every name in place of "Mr.Bean".

# GitHub Actions

If you want to use the action, you have to set a GRADLE_ENCRYPTION_KEY as secret in your repository:
https://docs.gradle.org/8.6/userguide/configuration_cache.html#config_cache:secrets:configuring_encryption_key
