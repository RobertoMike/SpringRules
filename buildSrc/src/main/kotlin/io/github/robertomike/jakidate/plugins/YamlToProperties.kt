package io.github.robertomike.jakidate.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream
import java.util.*

class YamlToProperties: Plugin<Project> {
    override fun apply(project: Project) {
        println("Converting YAML to properties...")
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

        val propertiesFile = buildDirObject.file("$buildDir/resources/main/ValidationMessages.properties")
        propertiesFile.get().asFile.createNewFile()

        val properties = Properties()

        flattenYaml(messages, properties)

        propertiesFile.get().asFile.outputStream().use {
            properties.store(it, null)
        }

        println("Finished converting YAML to properties...")
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