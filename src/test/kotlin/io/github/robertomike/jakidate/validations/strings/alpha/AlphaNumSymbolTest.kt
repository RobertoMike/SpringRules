package io.github.robertomike.jakidate.validations.strings.alpha

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/alpha/alphaNumSymbol")
class AlphaNumSymbolTest : YamlTest() {
    inner class Example(
        @field:AlphaNumSymbol
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
