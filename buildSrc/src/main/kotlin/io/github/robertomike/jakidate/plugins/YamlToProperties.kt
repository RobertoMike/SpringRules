package io.github.robertomike.jakidate.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.withType
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream
import java.util.*

class YamlToProperties: Plugin<Project> {
    private val logger = LoggerFactory.getLogger(YamlToProperties::class.java)

    override fun apply(project: Project) {
        project.tasks.withType<Jar>().configureEach {
            dependsOn("generateContributorMessage")
        }
        project.tasks.withType<Test>().configureEach {
            dependsOn("generateContributorMessage")
        }

        project.tasks.register("generateContributorMessage") {
            dependsOn(":processResources")

            logger.info("Converting YAML to properties...")

            val yaml = Yaml()

            val layout = project.layout
            val projectDirectory = layout.projectDirectory
            val messageFile = projectDirectory.file("src/main/resources/messages.yaml")

            val messages = yaml.load<Map<String, Any>>(FileInputStream(messageFile.asFile))

            // This is necessary because on linux take the base dir of the machine
            val buildDirObject = layout.buildDirectory
            val buildDir = buildDirObject.get().asFile.path
            val startPath = buildDirObject.file("$buildDir/resources/main")
            startPath.get().asFile.mkdirs()

            val propertiesFileOnResource =
                buildDirObject.file("$buildDir/resources/main/ContributorValidationMessages.properties")
            propertiesFileOnResource.get().asFile.createNewFile()

            val properties = Properties()

            flattenYaml(messages, properties)

            logger.info(properties.toString())
            logger.info("$buildDir/resources/main/ContributorValidationMessages.properties")

            propertiesFileOnResource.get().asFile.outputStream().use {
                properties.store(it, null)
            }

            logger.info("Finished converting YAML to properties...")
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun flattenYaml(map: Map<String, Any?>, properties: Properties, prefix: String = "") {
        map.forEach { (key, value) ->
            value ?: return@forEach

            val newKey = if (prefix.isNotEmpty()) "$prefix.$key" else key
            when (value) {
                is Map<*, *> -> flattenYaml(value as Map<String, Any>, properties, newKey)
                else -> properties.setProperty(newKey, value.toString())
            }
        }
    }
}