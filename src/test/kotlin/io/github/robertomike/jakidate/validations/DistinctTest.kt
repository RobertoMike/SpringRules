package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class DistinctTest : BaseTest() {
    inner class Example(
        @field:Distinct
        var value: Array<String> = arrayOf()
    )

    @Test
    fun validValue(validator: Validator) {
        val example = Example(arrayOf("a", "b", "c", "d"))

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun validEmptyValue(validator: Validator) {
        val example = Example()

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun invalidValue(validator: Validator) {
        val example = Example(arrayOf("a", "b", "c", "c"))

        val result = validator.validate(example)

        assert(result.isNotEmpty())
        checkMessages(result)
    }
}