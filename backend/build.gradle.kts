import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "3.0.0-RC1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.16"
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.spring") version "1.7.20"
    kotlin("plugin.jpa") version "1.7.20"
}

group = "io.mikael.poc"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    // maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    // runtimeOnly(project(":frontend"))
    implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
    implementation("io.opentelemetry.instrumentation:opentelemetry-spring-boot-starter:1.19.1-alpha")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<BootBuildImage> {
    buildpacks.set(listOf("gcr.io/paketo-buildpacks/bellsoft-liberica:9.9.0-ea", "gcr.io/paketo-buildpacks/java-native-image"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    archiveFileName.set("app.jar")
}
