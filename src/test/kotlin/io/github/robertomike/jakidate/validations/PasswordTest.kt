package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PasswordTest : BaseTest() {
    inner class Example(
        @field:Password(maxLength = 20)
        val value: String,
    )
    inner class OtherExample(
        @field:Password(
            specialCharacters = false,
            uppercaseAndLowercase = false,
            letters = false,
            digit = false
        )
        val value: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            "k20dm.sk02SAj03*",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("values")
    fun error(toCheck: String, validator: Validator) {
        val example = Example(toCheck)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }

    @Test
    fun errorToShort(validator: Validator) {
        val example = Example("1")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }

    @ParameterizedTest
    @MethodSource("values")
    fun withoutLimitation(toCheck: String, validator: Validator) {
        val example = OtherExample(toCheck)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    companion object {
        @JvmStatic
        fun values(): Stream<String> {
            return Stream.of(
                "12345567894", "sdasdaggf", "ASDASDASDA", "*********",
                "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"
            )
        }
    }
}