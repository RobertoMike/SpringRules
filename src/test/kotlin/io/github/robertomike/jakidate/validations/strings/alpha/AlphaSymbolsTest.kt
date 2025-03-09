package io.github.robertomike.jakidate.validations.strings.alpha

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/alpha/alphaSymbols")
class AlphaSymbolsTest : YamlTest() {
    inner class Example(
        @field:AlphaSymbols
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
