package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class HSLTest : BaseTest() {
    inner class Example(
        @field:HSLColor
        val color: String
    )

    @ParameterizedTest
    @YamlSource("/colors/hsl", "good")
    fun good(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/colors/hsl", "wrong")
    fun wrong(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}
