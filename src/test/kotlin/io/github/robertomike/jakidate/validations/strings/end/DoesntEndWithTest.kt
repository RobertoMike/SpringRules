package io.github.robertomike.jakidate.validations.strings.end

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.strings.DoesntEndWith
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class DoesntEndWithTest : BaseTest() {
    inner class Example(
        @field:DoesntEndWith("Alighieri")
        val name: String
    )

    @Test
    fun good(validator: Validator) {
        val example = Example("Alighieri Dante")

        val constraints = validator.validate(example)
        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example("Dante Alighieri")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
    }
}
