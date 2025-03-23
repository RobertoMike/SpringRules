package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.numbers.MinDigits
import javax.validation.Validator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MinDigitsTest : BaseTest() {
    inner class Example(
        @field:MinDigits(3)
        val value: Number,
    )

    @ParameterizedTest
    @MethodSource("goodOptions")
    fun good(value: Number, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("badOptions")
    fun error(value: Number, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }

    companion object {
        @JvmStatic
        fun goodOptions(): Stream<Number> {
            return Stream.of(
                150, 10.0, 10.0f, 105L, 104.toShort(), 103.toByte(), 130.toBigInteger(), 140.toBigDecimal(),
            )
        }
        @JvmStatic
        fun badOptions(): Stream<Number> {
            return Stream.of(
                5, 5.0, 5.0f, 5L, 5.toShort(), 5.toByte(), 5.toBigInteger(), 5.toBigDecimal(),
            )
        }
    }
}