plugins {
    kotlin("jvm")
}

group = "io.github.robertomike"
version = "1.0.0"

repositories {
    mavenCentral()
}

val springVersion = "3.0.0"

dependencies {
    implementation(project(":"))
    implementation("org.springframework.boot:spring-boot-starter-validation:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springVersion")

    api(project(":"))

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks.test {
    useJUnitPlatform()
}