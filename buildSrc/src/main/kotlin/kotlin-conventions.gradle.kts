import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("java-conventions")

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm")

    // A tool to detect kotlin problems. It's nice, give it a try!
    id("io.gitlab.arturbosch.detekt")
    // Disabled because of https://github.com/detekt/detekt/issues/6958
    //  id("io.github.detekt.gradle.compiler-plugin")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain {
        languageVersion.set(
            JavaLanguageVersion.of(libs.findVersion("jdk").get().toString())
        )
    }
    compilerOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs.add("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget.set(JvmTarget.valueOf("JVM_${libs.findVersion("jdk").get()}"))
        languageVersion.set(
            KotlinVersion.valueOf(
                "KOTLIN_${
                    libs.findVersion("kotlin").get().toString().substringBeforeLast(".").replace(".", "_")
                }"
            )
        )
        apiVersion.set(
            KotlinVersion.valueOf(
                "KOTLIN_${
                    libs.findVersion("kotlin").get().toString().substringBeforeLast(".").replace(".", "_")
                }"
            )
        )
    }
}

detekt {
    // Builds the AST in parallel. Rules are always executed in parallel.
    // It Can lead to speedups in larger projects. It's `false` by default.
    parallel = true

    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
    config.setFrom("$rootDir/detekt.yml")

    // Applies the config files on top of detekt's default config file. `false` by default.
    buildUponDefaultConfig = true

    // Autocorrects all fixable issues locally.
    if (System.getenv("CI").isNullOrEmpty()) {
        autoCorrect = true
    }
}

tasks.withType<Detekt>().configureEach {
    exclude("**/generated/**")
}

tasks.check {
    dependsOn(tasks.detektMain)
}

tasks.check.configure {
    this.setDependsOn(
        this.dependsOn.filterNot {
            it is TaskProvider<*> && it == tasks.detekt
        }
    )
}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    add("detektPlugins", libs.findLibrary("detekt-formatting").get())
}
