package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.examples.User
import jakarta.validation.Validation
import org.junit.jupiter.api.Test

class StartsWithTest {
    @Test
    fun good() {
        val user = User("Jake alexander")

        val factory = Validation.buildDefaultValidatorFactory()
        val validator = factory.validator

        val constraints = validator.validate(user)

        assert(constraints.isEmpty())
    }
}