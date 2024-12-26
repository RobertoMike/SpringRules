package io.github.robertomike.jakidate.validations.strings.end

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.strings.EndsWith
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class EndsWithTest : BaseTest() {
    inner class Example(
        @field:EndsWith("lumaca")
        val name: String
    )

    @Test
    fun good(validator: Validator) {
        val example = Example("Some random sentence with lumaca")

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example("I don't have lumaca at the end")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}
