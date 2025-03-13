package io.github.robertomike.jakidate.validations.ip

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest
import io.github.robertomike.jakidate.validations.web.ip.Ip

@YamlFileSource("/ip/ipv4")
@YamlFileSource("/ip/ipv6")
class IpTest : YamlTest() {
    inner class Example(
        @field:Ip
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
