plugins {
    kotlin("jvm") version "2.1.0"

    id("java-library")
    `maven-publish`
    id("signing")
}

group = "io.github.robertomike"
version = "0.0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

var jakartaVersion = "3.0.0"

dependencies {
    implementation("jakarta.validation:jakarta.validation-api:$jakartaVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
