package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExcludeIfTest : BaseTest() {
    @Exclude
    inner class Example(
        @field:Conditional
        val control: Boolean,
        @field:ExcludeIf
        val password: String?,
        @field:Conditional("email")
        val condition: String,
        @field:ExcludeIf(key = "email")
        val email: String?,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            true,
            "",
            "yes",
            "",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            true,
            "someRandomPassword",
            "yes",
            "email@mail.com",
        )

        val constraints = validator.validate(example)

        assertEquals(2, constraints.size)
        assert(constraints.any { it.propertyPath.first().name == "password" })
        assert(constraints.any { it.propertyPath.first().name == "email" })
    }

    @Test
    fun onlyOneError(validator: Validator) {
        val example = Example(
            false,
            null,
            "on",
            "email@mail.com",
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }
}