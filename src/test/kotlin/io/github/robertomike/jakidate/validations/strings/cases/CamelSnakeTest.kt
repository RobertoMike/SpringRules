package io.github.robertomike.jakidate.validations.strings.cases

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/cases/camelSnake")
class CamelSnakeTest : YamlTest() {
    inner class Example(
        @field:CamelSnakeCase
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
