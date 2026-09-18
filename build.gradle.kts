plugins {
    kotlin("jvm") version "2.3.20" // States that this project uses Kotlin and specifies version

    id("java-library") // States that this project is a Java library
    id("com.vanniktech.maven.publish") version "0.37.0" // Publishes to Maven Central via the Central Portal
    id("yaml-to-properties")
    `java-test-fixtures`
}

group = "io.github.robertomike"
version = "3.0.0"

// Specifies the Java version used to build the project
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Specifies the repositories used to download dependencies below
repositories {
    mavenCentral()
}

var jakartaVersion = "3.1.1"

// Specifies the dependencies used in the project
dependencies {
    implementation("jakarta.validation:jakarta.validation-api:$jakartaVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    api("jakarta.validation:jakarta.validation-api:$jakartaVersion")

    testImplementation("org.hibernate.validator:hibernate-validator:9.1.3.Final")
    testImplementation("org.glassfish.expressly:expressly:6.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.yaml:snakeyaml:2.7")
    testImplementation("org.junit.jupiter:junit-jupiter-params:6.1.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.slf4j:slf4j-api:2.0.19")
    testImplementation("ch.qos.logback:logback-classic:1.6.3")
}

// Specifies the build version for Java
kotlin {
    jvmToolchain(17)
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
        artifactId = "jakidate",
        version = project.version.toString()
    )

    pom {
        name = "Jakidate"
        description = "This is an open-source Java library that provides validation rules for every java project that use Jakarta to validate."
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