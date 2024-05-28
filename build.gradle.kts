import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.6"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

group = "app.onestar"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val jakartaPersistenceApiVersion = "3.1.0"
val jsonwebtokenVersion = "0.11.5"
val springdocOpenApiVersion = "1.7.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework:spring-jdbc")
    implementation("jakarta.persistence:jakarta.persistence-api:$jakartaPersistenceApiVersion")
    implementation("io.jsonwebtoken:jjwt-api:$jsonwebtokenVersion")
    implementation("io.jsonwebtoken:jjwt-impl:$jsonwebtokenVersion")
    implementation("io.jsonwebtoken:jjwt-jackson:$jsonwebtokenVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springdocOpenApiVersion")
    implementation("org.springdoc:springdoc-openapi-webflux-core:$springdocOpenApiVersion")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:r2dbc")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
