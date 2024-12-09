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
}

gradlePlugin {
    plugins {
        register("yaml-to-properties") {
            id = "yaml-to-properties"
            implementationClass = "io.github.robertomike.jakidate.plugins.YamlToProperties"
        }
    }
}