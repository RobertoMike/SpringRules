package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/colors/hsl")
class HSLTest : YamlTest() {
    inner class Example(
        @field:HSLColor
        val color: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
