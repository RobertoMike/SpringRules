package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.numbers.MultipleOf
import javax.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MultipleOfTest : BaseTest() {
    inner class Example(
        @field:MultipleOf("2")
        val value: Any,
    )

    @ParameterizedTest
    @MethodSource("goodOptions")
    fun good(value: Any, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("badOptions")
    fun error(value: Any, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }

    @Test
    fun unsupported(validator: Validator) {
        val example = Example("some random thing")

        assertThrows<Exception> {
            validator.validate(example)
        }
    }

    companion object {
        @JvmStatic
        fun goodOptions(): Stream<Any> {
            return Stream.of(
                10, 10.0, 10.0f, 10L, 10.toShort(), 10.toByte(), 10.toBigInteger(), 10.toBigDecimal(),
            )
        }
        @JvmStatic
        fun badOptions(): Stream<Any> {
            return Stream.of(
                5, 5.0, 5.0f, 5L, 5.toShort(), 5.toByte(), 5.toBigInteger(), 5.toBigDecimal(),
            )
        }
    }
}