plugins {
    kotlin("jvm") version "2.3.20"

    id("java-library")
    id("com.vanniktech.maven.publish") version "0.37.0"
    id("yaml-to-properties")
}

group = "io.github.robertomike"
version = "3.0.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

val springBootVersion = "4.1.1"
val springVersion = "7.0.8"

dependencies {
    implementation(project(":"))
    implementation("org.hibernate.validator:hibernate-validator:9.1.3.Final")

    api("org.springframework:spring-web:${springVersion}")
    api("org.springframework:spring-context:$springVersion")
    api("org.springframework.boot:spring-boot-autoconfigure:$springBootVersion")

    api(project(":"))

    testImplementation("org.springframework.boot:spring-boot-starter-validation:${springBootVersion}")
    testImplementation("org.springframework.boot:spring-boot-test:${springBootVersion}")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation(kotlin("test"))
    testImplementation(project(":", "testArtifacts"))
    testImplementation("org.mockito:mockito-core:5.23.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType(JavaCompile::class).configureEach {
    options.encoding = "UTF-8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// Publishes the library to Maven Central through the Sonatype Central Portal.
// OSSRH (s01.oss.sonatype.org) was decommissioned; this plugin targets the new portal API.
mavenPublishing {
    publishToMavenCentral(automaticRelease = true)

    // Only sign if credentials are available (CI environment)
    if (project.hasProperty("signing.keyId")) {
        signAllPublications()
    }

    coordinates(
        groupId = project.group.toString(),
        artifactId = "spring-rules",
        version = project.version.toString()
    )

    pom {
        name = "Spring rules"
        description = "This is an open-source Java library that provides validation rules for Spring applications."
        url = "https://github.com/RobertoMike/Jakidate"
        inceptionYear = "2025"

        licenses {
            license {
                name = "MIT License"
                url = "http://www.opensource.org/licenses/mit-license.php"
            }
        }
        developers {
            developer {
                name = "Roberto Micheletti"
                email = "rmworking@hotmail.com"
                organization = "Kaiten"
                organizationUrl = "https://github.com/RobertoMike"
            }
            developer {
                name = "Giorgio Andrei"
                email = "giorgio.work24@gmail.com"
                organization = "Kaiten"
                organizationUrl = "https://github.com/RobertoMike"
            }
        }
        scm {
            connection = "scm:git:git://github.com/RobertoMike/SpringRules.git"
            developerConnection = "scm:git:ssh://github.com:RobertoMike/SpringRules.git"
            url = "https://github.com/RobertoMike/SpringRules"
        }
    }
}
