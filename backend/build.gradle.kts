import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("org.springframework.boot") version "3.4.4"
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.spring") version "2.1.20"
    kotlin("plugin.jpa") version "2.1.20"
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

configurations {
    create("otelAgent")
}

dependencies {
    implementation(project(":frontend"))
    implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    constraints {
        implementation("org.jboss.threads:jboss-threads:3.8.0.Final") {
            because("Java 24 support")
        }
    }
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    runtimeOnly("io.micrometer:micrometer-registry-otlp")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    "otelAgent"("io.opentelemetry.javaagent:opentelemetry-javaagent:2.15.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    archiveFileName = "app.jar"
}

tasks.register<Copy>("agent") {
    group = "Application"
    description = "Downloads OpenTelemetry agent to the target directory"

    from(configurations["otelAgent"])
    into("${buildDir}/libs")
    rename { "opentelemetry-javaagent.jar" }

    doLast {
        logger.lifecycle("OpenTelemetry agent downloaded to: ${destinationDir}/opentelemetry-javaagent.jar")
    }
}
