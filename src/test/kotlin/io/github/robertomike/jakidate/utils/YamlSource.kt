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
    val file: String,
    val element: String
) {
    class YamlSourceProvider : AnnotationBasedArgumentsProvider<YamlSource>() {
        override fun provideArguments(context: ExtensionContext, annotation: YamlSource): Stream<out Arguments> {
            val strings = loadAndGetYaml(annotation.file)[annotation.element]
                ?: throw RuntimeException("Value ${annotation.element} not found in file ${annotation.file}")

            return strings.stream()
                .map(Arguments::of)
        }

        private fun loadAndGetYaml(fileName: String): Map<String, List<String>> {
            val file = BaseTest::class.java.getResource("$fileName.yaml")?.file
                ?: throw RuntimeException("File $fileName not found, example stringsEan == /strings/en.yml")

            val yaml = Yaml()
            return yaml.load(FileInputStream(file))
        }
    }
}
