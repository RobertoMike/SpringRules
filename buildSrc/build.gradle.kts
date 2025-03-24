plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

object Versions {
    const val KOTLIN = "2.1.0"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-serialization:${Versions.KOTLIN}")
    implementation("org.yaml:snakeyaml:2.0")
    testImplementation("org.slf4j:slf4j-api:1.7.36")
    testImplementation("ch.qos.logback:logback-classic:1.4.12")
}

gradlePlugin {
    plugins {
        register("yaml-to-properties") {
            id = "yaml-to-properties"
            implementationClass = "io.github.robertomike.jakidate.plugins.YamlToProperties"
        }
        register("generate-constraint-validator-meta-file") {
            id = "generate-constraint-validator-meta-file"
            implementationClass = "io.github.robertomike.jakidate.plugins.GenerateConstraintValidatorMetaFile"
        }
    }
}