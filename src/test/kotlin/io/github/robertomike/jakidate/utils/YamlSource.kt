package io.github.robertomike.jakidate.utils

import io.github.robertomike.jakidate.BaseTest
import org.apiguardian.api.API
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsSource
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream
import java.util.stream.Stream

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@API(status = API.Status.STABLE, since = "5.7")
@Repeatable
@ArgumentsSource(YamlSource.YamlSourceProvider::class)
annotation class YamlSource(
    val file: String = "",
    val element: String
) {
    class YamlSourceProvider : AnnotationBasedArgumentsProvider<YamlSource>() {
        override fun provideArguments(context: ExtensionContext, annotation: YamlSource): Stream<out Arguments> {
            val strings = loadAndGetValues(getFileNames(context, annotation), annotation.element)

            return strings.stream()
                .map(Arguments::of)
        }

        private fun loadAndGetValues(fileNames: List<String>, element: String): List<String> {
            val files = fileNames.map {
                BaseTest::class.java.getResource("$it.yaml")?.file
                    ?: throw RuntimeException("File $it not found, example stringsEan == /strings/en.yml")
            }

            val yaml = Yaml()
            return files.flatMap {
                val values = yaml.load(FileInputStream(it)) as Map<String, List<String>>
                values[element] ?: throw RuntimeException("Value $element not found in one of the files")
            }.ifEmpty {
                throw RuntimeException("There are no values for $element found in none of the files")
            }
        }

        private fun getFileNames(context: ExtensionContext, annotation: YamlSource): List<String> {
            val fileNames = when (annotation.file) {
                "" -> context.requiredTestClass.getAnnotationsByType(YamlFileSource::class.java).map { it.file }
                else -> listOf(annotation.file)
            }
            if (fileNames.isEmpty()) {
                throw RuntimeException("You need to define where is the file inside the YamlFileSource annotation or YamlSource")
            }

            return fileNames
        }
    }
}
