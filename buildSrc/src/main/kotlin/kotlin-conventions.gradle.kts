import de.mrclrchtr.education.gradle.constant.JDK_VERSION
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("java-conventions")

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    kotlin("jvm")

    // A tool to detect kotlin problems. It's nice, give it a try!
    id("io.gitlab.arturbosch.detekt")
}

// Needs to exist before first usage of 'libs'
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
        allWarningsAsErrors = false
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
    ignoreFailures = false

    // Applies the config files on top of detekt's default config file. `false` by default.
    buildUponDefaultConfig = true

    // Define the detekt configuration(s) you want to use.
    // Defaults to the default detekt configuration.
    config.setFrom("$rootDir/detekt.yml")

    // Builds the AST in parallel. Rules are always executed in parallel.
    // Can lead to speedups in larger projects. `false` by default.
    parallel = true
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

// Activate Type Resolution
tasks.withType<Detekt>().configureEach {
    this.jvmTarget = JDK_VERSION
    classpath.setFrom(
        sourceSets.main.get().compileClasspath,
        sourceSets.test.get().compileClasspath
    )
}

// Activate Type Resolution
tasks.withType<DetektCreateBaselineTask>().configureEach {
    this.jvmTarget = JDK_VERSION
    classpath.setFrom(
        sourceSets.main.get().compileClasspath,
        sourceSets.test.get().compileClasspath
    )
}

afterEvaluate {
    // Workaround for https://detekt.dev/docs/gettingstarted/gradle/#gradle-runtime-dependencies
    // and https://github.com/detekt/detekt/issues/6428#issuecomment-1779291878
    configurations.matching { it.name == "detekt" }.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion(libs.findVersion("kotlinForDetekt").get().toString())
            }
        }
    }
}
