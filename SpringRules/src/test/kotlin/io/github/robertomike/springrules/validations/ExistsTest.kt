package io.github.robertomike.springrules.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class ExistsTest : BaseTest() {
    inner class Repository {
        fun findById(id: Long): Any? {
            if (id > 1L) {
                return null
            }
            return Example(1L)
        }
    }

    inner class Example(
        @field:Exists(repository = Repository::class)
        var userId: Long
    )

    @Test
    fun valid(validator: Validator) {
        val example = Example(1L)

        val errors = validator.validate(example)

        assert(errors.isEmpty())
    }

    @Test
    fun notValid(validator: Validator) {
        val example = Example(2L)

        val errors = validator.validate(example)

        assert(errors.isNotEmpty())
    }
}