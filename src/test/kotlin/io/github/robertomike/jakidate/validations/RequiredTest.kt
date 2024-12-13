package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.objects.required.Required
import io.github.robertomike.jakidate.validations.objects.required.RequiredIf
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RequiredTest : BaseTest() {
    @Required
    inner class Example(
        @field:RequiredIf(true)
        val control: Boolean,
        @field:RequiredIf
        val password: String?,
        @field:RequiredIf(true, "email")
        val conditional: String,
        @field:RequiredIf(key = "email")
        val email: String?,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            true,
            "1234",
            "yes",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            true,
            null,
            "yes",
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
            false,
            null,
            "on",
            null,
        )

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        assertEquals("email", constraints.first().propertyPath.first().name)
    }
}