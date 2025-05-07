package io.github.robertomike.jakidate.validations.web

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/web/macAddress")
class MacAddressTest : YamlTest() {
    inner class Example(
        @field:MacAddress
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
