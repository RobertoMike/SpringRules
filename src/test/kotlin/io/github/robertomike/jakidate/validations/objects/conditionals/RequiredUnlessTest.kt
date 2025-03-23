package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RequiredUnlessTest : BaseTest() {
    @Test
    fun good(validator: Validator) {
        val example = Example(
            false,
            "1234",
            "0",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            false,
            null,
            "off",
            "",
        )

        val constraints = validator.validate(example)

        assertEquals(2, constraints.size)
        assert(constraints.any { it.propertyPath.first().name == "password" })
        assert(constraints.any { it.propertyPath.first().name == "email" })
    }

    @Test
    fun onlyOneError(validator: Validator) {
        val example = Example(
            true,
            null,
            "false",
            null,
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }


    @Required
    inner class Example(
        @field:Conditional
        val control: Boolean,
        @field:RequiredUnless
        val password: String?,
        @field:Conditional("email")
        val condition: String,
        @field:RequiredUnless(key = "email")
        val email: String?,
    )
}