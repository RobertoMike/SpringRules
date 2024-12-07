package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.CustomTest
import io.github.robertomike.jakidate.examples.User
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

@CustomTest
class StartsWithTest {
    @Test
    fun good(validator: Validator) {
        val user = User("Jake alexander")

        val constraints = validator.validate(user)

        assert(constraints.isEmpty())
    }
}