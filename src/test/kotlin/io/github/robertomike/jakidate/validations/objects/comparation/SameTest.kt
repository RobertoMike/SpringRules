package io.github.robertomike.jakidate.validations.objects.comparation

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.objects.Same
import io.github.robertomike.jakidate.validations.objects.SameAs
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SameTest : BaseTest() {
    @Same
    inner class Example(
        @field:SameAs
        val password: String,
        @field:SameAs
        val passwordConfirmation: String,
        @field:SameAs("email")
        val email: String,
        @field:SameAs("email")
        val emailConfirmation: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            "1234",
            "1234",
            "user@mail.com",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            "1234",
            "12345",
            "user@mail.com",
            "bho@mail.com",
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
            "1234",
            "12345",
            "user@mail.com",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        assertEquals("password", constraints.first().propertyPath.first().name)
    }
}