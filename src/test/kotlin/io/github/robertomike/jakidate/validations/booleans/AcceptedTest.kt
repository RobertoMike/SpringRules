package io.github.robertomike.jakidate.validations.booleans

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertFails

class AcceptedTest : BaseTest() {
    inner class Example(
        @field:Accepted
        var value: Any? = null
    )


    @Test
    fun validValue(validator: Validator) {
        val example = Example(true)

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidValue(validator: Validator) {
        val example = Example(false)

        val result = validator.validate(example)

        assert(result.isNotEmpty())
        checkMessages(result)
    }

    @ParameterizedTest
    @ValueSource(strings = ["yes", "on", "true", "y", "1"])
    fun validStringValues(accepted: String, validator: Validator) {
        val example = Example(accepted)

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["no", "off", "false", "n", "0"])
    fun invalidStringValues(accepted: String, validator: Validator) {
        val example = Example(accepted)

        val result = validator.validate(example)

        assert(result.isNotEmpty())
    }

    @Test
    fun validNumberValue(validator: Validator) {
        val example = Example(1)

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidNumberValue(validator: Validator) {
        val example = Example(0)

        val result = validator.validate(example)

        assert(result.isNotEmpty())
    }

    @Test
    fun unsupportedType(validator: Validator) {
        val example = Example(Example())

        assertFails { validator.validate(example) }
    }
}