package io.github.robertomike.jakidate.validations.documents.us

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/documents/us/issn")
class ISSNTest : YamlTest() {
    inner class Example(
        @field:ISSN
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
