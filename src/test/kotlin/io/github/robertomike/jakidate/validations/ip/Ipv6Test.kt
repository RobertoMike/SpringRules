package io.github.robertomike.jakidate.validations.ip

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/ip/ipv6")
class Ipv6Test : YamlTest() {
    inner class Example(
        @field:Ipv6
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
