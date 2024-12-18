package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExcludeUnlessTest : BaseTest() {
    @Exclude
    inner class Example(
        @field:ExcludeUnless(true)
        val control: Boolean,
        @field:ExcludeUnless
        val password: String?,
        @field:ExcludeUnless(true, "email")
        val conditional: String,
        @field:ExcludeUnless(key = "email")
        val email: String?,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            false,
            "",
            "0",
            "",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            false,
            "someRandomPassword",
            "no",
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
            true,
            null,
            "off",
            "email@mail.com",
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }
}