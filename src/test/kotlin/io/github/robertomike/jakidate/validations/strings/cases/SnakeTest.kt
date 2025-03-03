package io.github.robertomike.jakidate.validations.strings.cases

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/cases/snake")
class SnakeTest : YamlTest() {
    inner class Example(
        @field:SnakeCase
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
