package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.springrules.validations.Exists
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class ExistsConstraintTest : BaseTest() {
    inner class Repository {
        fun findById(id: Long): Any? {
            return null
        }
    }

    inner class Example(
        @field:Exists(repository = Repository::class)
        var userId: Long
    )

    @Test
    fun notValid(validator: Validator) {
        val example = Example(1L)

        val errors = validator.validate(example)

        assert(errors.isNotEmpty())
    }
}