package io.github.robertomike.jakidate.validations.strings.start

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class StartsWithTest : BaseTest() {
    inner class Example(
        @field:StartsWith("Jake")
        val name: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example("Jake Alexander")

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example("alexander Jake")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}