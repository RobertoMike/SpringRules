package io.github.robertomike.jakidate

import io.github.robertomike.jakidate.configs.CustomTest
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream

@CustomTest
abstract class BaseTest {
    companion object {
        fun loadAndGetYaml(fileName: String): Map<String, List<String>> {
            val file = BaseTest::class.java.getResource("$fileName.yaml")?.file
                ?: throw RuntimeException("File $fileName not found, example stringsEan == /strings/en.yml")

            val yaml = Yaml()
            return yaml.load(FileInputStream(file))
        }
    }
}
