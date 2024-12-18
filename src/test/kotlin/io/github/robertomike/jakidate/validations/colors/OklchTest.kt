package io.github.robertomike.jakidate.validations.colors

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.YamlSource
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest

class OklchTest : BaseTest() {
    inner class Example(
        @field:OklchColor
        val color: String
    )

    @ParameterizedTest
    @YamlSource("/colors/oklch", "good")
    fun good(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @YamlSource("/colors/oklch", "wrong")
    fun wrong(color: String, validator: Validator) {
        val example = Example(color)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
    }
}
