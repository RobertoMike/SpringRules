package io.github.robertomike.jakidate.validations.objects.comparations

import io.github.robertomike.jakidate.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DifferentTest : BaseTest() {
    @Different
    inner class Example(
        @field:DifferentAs
        val password: String,
        @field:DifferentAs
        val passwordConfirmation: String,
        @field:DifferentAs("email")
        val email: String,
        @field:DifferentAs("email")
        val emailConfirmation: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            "1234",
            "12345",
            "user@mail.comm",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            "1234",
            "1234",
            "user@mail.com",
            "user@mail.com",
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
        assertEquals("email", constraints.first().propertyPath.first().name)
    }
}