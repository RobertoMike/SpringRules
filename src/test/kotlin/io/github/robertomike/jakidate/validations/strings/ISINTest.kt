package io.github.robertomike.jakidate.validations.strings

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/isin")
class ISINTest : YamlTest() {
    inner class Example(
        @field:ISIN
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
