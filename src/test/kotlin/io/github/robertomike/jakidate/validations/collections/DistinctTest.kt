package io.github.robertomike.jakidate.validations.collections

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.collections.Distinct
import javax.validation.Validator
import org.junit.jupiter.api.Test

class DistinctTest : BaseTest() {
    inner class Example(
        @field:Distinct
        var value: Array<String> = arrayOf()
    )
    inner class ExampleList(
        @field:Distinct
        var value: List<String> = listOf()
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

    @Test
    fun listValidValue(validator: Validator) {
        val example = ExampleList(listOf("a", "b", "c", "d"))

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun listValidEmptyValue(validator: Validator) {
        val example = ExampleList()

        val result = validator.validate(example)

        assert(result.isEmpty())
    }

    @Test
    fun listInvalidValue(validator: Validator) {
        val example = ExampleList(listOf("a", "b", "c", "c"))

        val result = validator.validate(example)

        assert(result.isNotEmpty())
        checkMessages(result)
    }
}