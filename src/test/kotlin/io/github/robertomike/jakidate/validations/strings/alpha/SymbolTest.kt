package io.github.robertomike.jakidate.validations.strings.alpha

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/alpha/symbol")
class SymbolTest : YamlTest() {
    inner class Example(
        @field:Symbol
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
