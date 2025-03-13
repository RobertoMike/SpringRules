package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.collections.Contains
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.Collections
import java.util.stream.Stream

class ContainsTest : BaseTest() {
    inner class Example(
        @field:Contains(["10", "2", "3"])
        val value: Any,
    )

    inner class ExampleAnd(
        @field:Contains(["2", "3"], operator = Contains.Operator.AND)
        val value: Map<String, String>,
    )

    @ParameterizedTest
    @MethodSource("goodOptions")
    fun good(value: Any, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun goodAnd(validator: Validator) {
        val example = ExampleAnd(
            mapOf("2" to "Hello", "3" to "World")
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun badAnd(validator: Validator) {
        val example = ExampleAnd(
            mapOf("5" to "Hello", "3" to "World")
        )

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
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
        val example = Example(listOf<String>())

        assertThrows<Exception> {
            validator.validate(example)
        }
    }

    companion object {
        @JvmStatic
        fun goodOptions(): Stream<Any> {
            return Stream.of(
                10, 10.0, 10.0f, 10L, 10.toShort(), 10.toByte(), 10.toBigInteger(), 10.toBigDecimal(),
                Collections.singletonMap("2", "Hello"), "3"
            )
        }
        @JvmStatic
        fun badOptions(): Stream<Any> {
            return Stream.of(
                5, 5.0, 5.0f, 5L, 5.toShort(), 5.toByte(), 5.toBigInteger(), 5.toBigDecimal(),
                Collections.singletonMap("some_random_key", "Hello"), "15"
            )
        }
    }
}