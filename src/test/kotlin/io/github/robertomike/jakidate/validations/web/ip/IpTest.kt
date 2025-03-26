package io.github.robertomike.jakidate.validations.web.ip

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/web/ip/ipv4")
@YamlFileSource("/web/ip/ipv6")
class IpTest : YamlTest() {
    inner class Example(
        @field:Ip
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
