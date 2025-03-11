package io.github.robertomike.jakidate.validations.strings.alpha

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/alpha/alphaNum")
class AlphaNumTest : YamlTest() {
    inner class Example(
        @field:AlphaNum
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
