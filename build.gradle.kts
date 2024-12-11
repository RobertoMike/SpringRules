plugins {
    kotlin("jvm") version "2.1.0"

    id("java-library")
    `maven-publish`
    id("signing")
    id("yaml-to-properties")
}

group = "io.github.robertomike"
version = "2.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

var jakartaVersion = "3.0.0"

dependencies {
    implementation("jakarta.validation:jakarta.validation-api:$jakartaVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    api("jakarta.validation:jakarta.validation-api:$jakartaVersion")

    testImplementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    testImplementation("org.glassfish.expressly:expressly:5.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(11)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
