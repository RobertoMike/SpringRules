package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.examples.Animal
import io.github.robertomike.jakidate.examples.User
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class StartsWithTest : BaseTest() {
    @Test
    fun good(validator: Validator) {
        val user = User("Jake Alexander")

        val constraints = validator.validate(user)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val user = User("alexander Jake")

        val constraints = validator.validate(user)

        assert(constraints.isNotEmpty())
    }
}