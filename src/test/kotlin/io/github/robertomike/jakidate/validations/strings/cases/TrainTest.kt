package io.github.robertomike.jakidate.validations.strings.cases

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest

@YamlFileSource("/strings/cases/train")
class TrainTest : YamlTest() {
    inner class Example(
        @field:TrainCase
        val value: String
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }
}
