package io.github.robertomike.jakidate.validations.strings

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest
import io.github.robertomike.jakidate.validations.documents.us.ISSN

@YamlFileSource("/strings/issn")
class ISSNTest : YamlTest() {
    inner class Example(
        @field:ISSN
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
