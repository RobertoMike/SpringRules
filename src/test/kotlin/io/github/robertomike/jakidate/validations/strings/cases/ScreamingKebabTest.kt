package io.github.robertomike.jakidate.validations.strings.cases

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/cases/screamingKebab")
class ScreamingKebabTest : YamlTest() {
    inner class Example(
        @field:ScreamingKebabCase
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
