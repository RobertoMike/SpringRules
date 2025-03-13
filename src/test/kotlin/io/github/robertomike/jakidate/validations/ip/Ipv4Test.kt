package io.github.robertomike.jakidate.validations.ip

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest
import io.github.robertomike.jakidate.validations.web.ip.Ipv4

@YamlFileSource("/ip/ipv4")
class Ipv4Test : YamlTest() {
    inner class Example(
        @field:Ipv4
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
