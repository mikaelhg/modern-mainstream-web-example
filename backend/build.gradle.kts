import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "4.0.0"
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
}

group = "io.mikael.poc"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
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
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    runtimeOnly("io.micrometer:micrometer-registry-otlp")
    otelAgent("io.opentelemetry.javaagent:opentelemetry-javaagent:2.21.0")

    implementation("org.springframework.boot:spring-boot-starter-flyway")
    implementation("org.flywaydb:flyway-database-postgresql")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.testcontainers:testcontainers-postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(kotlin("test"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    mockitoAgent("org.mockito:mockito-core:5.20.0") {
        isTransitive = false
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-javaagent:${mockitoAgent.singleFile.absolutePath}")
    // jvmArgs("-Xshare:off")
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
    jvmToolchain(21)
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
