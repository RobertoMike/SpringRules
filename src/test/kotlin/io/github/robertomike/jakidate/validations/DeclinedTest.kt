package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class DeclinedTest : BaseTest() {
    inner class Example(
        @field:Declined
        var value: Any? = null
    )

    @Test
    fun validValue(validator: Validator) {
        val example = Example(false)

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidValue(validator: Validator) {
        val example = Example(true)

        val result = validator.validate(example)

        assert(result.isNotEmpty())
        checkMessages(result)
    }

    @Test
    fun validStringValue(validator: Validator) {
        val example = Example("no")

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidStringValue(validator: Validator) {
        val example = Example("yes")

        val result = validator.validate(example)

        assert(result.isNotEmpty())
    }

    @Test
    fun validNumberValue(validator: Validator) {
        val example = Example(0)

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidNumberValue(validator: Validator) {
        val example = Example(1)

        val result = validator.validate(example)

        assert(result.isNotEmpty())
    }
}