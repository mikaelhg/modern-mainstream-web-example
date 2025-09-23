import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "4.0.0-M3"
    kotlin("jvm") version "2.2.20"
    kotlin("plugin.spring") version "2.2.20"
    kotlin("plugin.jpa") version "2.2.20"
}

group = "io.mikael.poc"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

val otelAgent: Configuration by configurations.creating
val mockitoAgent: Configuration by configurations.creating

dependencies {
    implementation(project(":frontend"))
    implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    runtimeOnly("io.micrometer:micrometer-registry-otlp")
    runtimeOnly("org.postgresql:postgresql")
    otelAgent("io.opentelemetry.javaagent:opentelemetry-javaagent:2.20.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:postgresql")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    mockitoAgent("org.mockito:mockito-core:5.20.0") {
        isTransitive = false
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    jvmArgs("-Xshare:off")
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
    jvmToolchain(25)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    jvmTargetValidationMode.set(org.jetbrains.kotlin.gradle.dsl.jvm.JvmTargetValidationMode.WARNING)
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    archiveFileName = "app.jar"
}

tasks.register<Copy>("agent") {
    val buildDir = project.layout.buildDirectory.get().asFile.absolutePath
    group = "Application"
    description = "Downloads OpenTelemetry agent to the target directory"

    from(otelAgent)
    into("${buildDir}/libs")
    rename { "opentelemetry-javaagent.jar" }

    doLast {
        logger.lifecycle("OpenTelemetry agent downloaded to: ${destinationDir}/opentelemetry-javaagent.jar")
    }
}
