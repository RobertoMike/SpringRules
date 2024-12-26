package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExcludeTest : BaseTest() {
    @Exclude
    inner class Example(
        @field:ExcludeIf(true)
        val control: Boolean,
        @field:ExcludeIf
        val password: String?,
        @field:ExcludeUnless(true, "email")
        val conditional: String,
        @field:ExcludeUnless(key = "email")
        val email: String?,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            true,
            "",
            "false",
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
            "n",
            "email@mail.com",
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
            false,
            null,
            "0",
            "email@mail.com",
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }
}