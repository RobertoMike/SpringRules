package io.github.robertomike.jakidate.configs

import java.io.FileInputStream
import java.util.Properties

class Configuration(
    val baseTemplate: String
) {
    companion object {
        @JvmStatic
        var instance: Configuration

        init {
            val properties = Properties()
            properties.load(FileInputStream("META-INF/jakidate.properties"))

            instance = Configuration(
                properties.getProperty("base-template")
            )
        }
    }
}