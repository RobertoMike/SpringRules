package io.github.robertomike.jakidate.validations.numbers

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.utils.toDate
import io.github.robertomike.jakidate.utils.toLocalDateTime
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BetweenTest : BaseTest() {
    inner class Example(
        @field:Between("2", "5")
        val value: Any,
    )
    inner class ExampleDate(
        @field:Between("02-04-2012", "04-05-2014", "DD-MM-YYYY")
        val value: Any,
    )
    inner class ExampleFormatDateTime(
        @field:Between("2023-10-10T12:30:00", "2025-10-10T12:30:00")
        val value: Any,
    )

    @ParameterizedTest
    @MethodSource("goodOptions")
    fun good(value: Any, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun goodDate(validator: Validator) {
        val example = ExampleDate("07-10-2013".toDate("DD-MM-YYYY"))

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun goodLocalDateTime(validator: Validator) {
        val example = ExampleFormatDateTime("2024-10-10T12:30:00".toLocalDateTime())

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun badLocalDateTime(validator: Validator) {
        val example = ExampleFormatDateTime("2022-10-10T12:30:00".toLocalDateTime())

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }

    @Test
    fun badDate(validator: Validator) {
        val example = ExampleDate("07-10-2015".toDate("DD-MM-YYYY"))

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
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
                3, 4.5, 2.5f, 3L, 4.toShort(), 2.toByte(), 5.toBigInteger(), 3.toBigDecimal(),
            )
        }
        @JvmStatic
        fun badOptions(): Stream<Any> {
            return Stream.of(
                6, 1, 7.0, -1.0, 5.1f, 1.9f, 6L, 0L, 1.toShort(), 8.toShort(), 0.toByte(), 9.toByte(),
                15.toBigInteger(), (-24).toBigInteger(), 12.toBigDecimal(), 1.toBigDecimal(),
            )
        }
    }
}