plugins {
    kotlin("jvm") version "2.1.0"

    id("java-library")
    `maven-publish`
    id("signing")
    id("yaml-to-properties")
    id("generate-constraint-validator-meta-file")
}

group = "io.github.robertomike"
version = "2.0.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

generateConstraintValidatorMetaFile {
    packagePath = "io\\github\\robertomike\\springrules\\constraints"
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

val springVersion = "3.0.0"

dependencies {
    implementation(project(":"))
    implementation("org.springframework.boot:spring-boot-starter-validation:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springVersion")

    api("org.springframework.boot:spring-boot-starter-validation:$springVersion")
    api("org.springframework.boot:spring-boot-starter-web:$springVersion")
    api(project(":"))

    testImplementation(kotlin("test"))
    testImplementation(project(":", "testArtifacts"))
    testImplementation("org.mockito:mockito-core:5.16.0")
}

tasks.test {
    useJUnitPlatform()
}

// Library Publication
publishing {
    publications {
        register("library", MavenPublication::class) {
            from(components["java"])

            groupId = "$group"
            artifactId = "spring-rules"
            version = version

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
                    connection = "scm:git:git://github.com/RobertoMike/Jakidate.git"
                    developerConnection = "scm:git:ssh://github.com:RobertoMike/Jakidate.git"
                    url = "https://github.com/RobertoMike/Jakidate"
                }
            }
        }
    }
    repositories {
        maven {

            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("OSSRH_USERNAME")
                password = System.getenv("OSSRH_PASSWORD")
            }
            metadataSources {
                gradleMetadata()
            }
        }
    }
}

if (!project.hasProperty("local")) {
    signing {
        setRequired { !version.toString().endsWith("SNAPSHOT") }
        sign(publishing.publications["library"])
    }
}

tasks.withType(JavaCompile::class).configureEach {
    options.encoding = "UTF-8"
}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}