plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml:2.7")
    testImplementation("org.slf4j:slf4j-api:2.0.19")
    testImplementation("ch.qos.logback:logback-classic:1.6.3")
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