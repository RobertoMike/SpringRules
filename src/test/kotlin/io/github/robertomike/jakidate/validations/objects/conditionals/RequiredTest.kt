package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RequiredTest : BaseTest() {
    @Test
    fun good(validator: Validator) {
        val example = Example(
            "yes",
            "1234",
            "no",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            "yes",
            null,
            "false",
            "",
        )

        val constraints = validator.validate(example)

        assertEquals(2, constraints.size)
        assert(constraints.any { it.propertyPath.first().name == "password" })
        assert(constraints.any { it.propertyPath.first().name == "email" })
        checkMessages(constraints)
    }

    @Test
    fun onlyOneError(validator: Validator) {
        val example = Example(
            "no",
            null,
            "off",
            null,
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }

    @Required
    inner class Example(
        @field:RequiredIf(true)
        val control: String,
        @field:RequiredIf
        val password: String?,
        @field:RequiredUnless(true, "email")
        val condition: String,
        @field:RequiredUnless(key = "email")
        val email: String?,
    )
}