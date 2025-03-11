package io.github.robertomike.jakidate.validations.strings.alpha

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/alpha/alpha")
class AlphaTest : YamlTest() {
    inner class Example(
        @field:Alpha
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
