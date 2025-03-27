plugins {
    kotlin("jvm") version "2.1.0" // States that this project uses Kotlin and specifies version

    id("java-library") // States that this project is a Java library
    `maven-publish` // Add commands and configuration for publishing
    id("signing") // Used for Maven digital signature
    id("yaml-to-properties")
    `java-test-fixtures`
}

group = "io.github.robertomike"
version = "1.0.2"

// Specifies the Java version used to build the project
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// Specifies the repositories used to download dependencies below
repositories {
    mavenCentral()
}

var jakartaVersion = "2.0.1"

// Specifies the dependencies used in the project
dependencies {
    implementation("jakarta.validation:jakarta.validation-api:$jakartaVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("javax.el:javax.el-api:3.0.0")
    implementation("org.glassfish:javax.el:3.0.0")

    api("jakarta.validation:jakarta.validation-api:$jakartaVersion")

    testImplementation("org.hibernate.validator:hibernate-validator:6.2.0.Final")
    testImplementation("org.glassfish.expressly:expressly:5.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.yaml:snakeyaml:2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Specifies the build version for Java
kotlin {
    jvmToolchain(11)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Gives access to Jakidate tests to SpringRules
val testJar by tasks.registering(Jar::class) {
    archiveClassifier.set("${project.name}-tests")
    from(sourceSets.test.get().output)
}

configurations {
    create("testArtifacts")
}

artifacts {
    add("testArtifacts", testJar)
}

// Publishes the library
publishing {
    publications {
        register("library", MavenPublication::class) {
            from(components["java"])

            groupId = "$group" // The group ID of the library
            artifactId = "jakidate" // The artifact ID of the library
            version = version

            pom {
                name = "Jakidate"
                description = "This is an open-source Java library that provides validation rules for every java project that use Jakarta to validate."
                url = "https://github.com/RobertoMike/Jakidate"
                inceptionYear = "2025"

                // The license of the library
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
                scm { // Where our library will be hosted (GitHub)
                    connection = "scm:git:git://github.com/RobertoMike/SpringRules.git"
                    developerConnection = "scm:git:ssh://github.com:RobertoMike/SpringRules.git"
                    url = "https://github.com/RobertoMike/SpringRules"
                }
            }
        }
    }
    repositories { // Specifies the repositories used to publish the library
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

// If the local property is set, it doesn't execute the signing
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
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}