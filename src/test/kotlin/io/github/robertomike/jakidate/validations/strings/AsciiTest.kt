package io.github.robertomike.jakidate.validations.strings

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/Ascii")
class AsciiTest : YamlTest() {
    inner class Example(
        @field:Ascii
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
