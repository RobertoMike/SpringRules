package io.github.robertomike.jakidate.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream
import java.util.*

class YamlToProperties: Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("convertYamlToProperties") {
            doLast {
                val yaml = Yaml()
                val messages = yaml.load<Map<String, Any>>(FileInputStream("src/main/resources/messages.yml"))
                val propertiesFile = project.file("src/main/resources/ValidationMessages.properties")

                val properties = Properties()

                flattenYaml(messages, properties)

                propertiesFile.outputStream().use { outputStream ->
                    properties.store(outputStream, null)
                }
            }
        }
    }

    private fun flattenYaml(map: Map<String, Any>, properties: Properties, prefix: String = "") {
        map.forEach { (key, value) ->
            val newKey = if (prefix.isNotEmpty()) "$prefix.$key" else key
            when (value) {
                is Map<*, *> -> flattenYaml(value as Map<String, Any>, properties, newKey)
                else -> properties.setProperty(newKey, value.toString())
            }
        }
    }
}