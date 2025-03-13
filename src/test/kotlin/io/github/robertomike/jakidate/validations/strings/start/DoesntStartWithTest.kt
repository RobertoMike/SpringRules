package io.github.robertomike.jakidate.validations.strings.start

import io.github.robertomike.jakidate.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.Test

class DoesntStartWithTest : BaseTest() {
    inner class Example(
        @field:DoesntStartWith("Leonardo")
        val name: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example("Da Vinci Leonardo")

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example("Leonardo Da Vinci")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}