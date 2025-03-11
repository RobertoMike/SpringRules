package io.github.robertomike.springrules.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import java.util.*

class UniqueTest : BaseTest() {
    inner class Repository {
        fun findById(id: Long): Optional<Example> {
            if (id > 1L) {
                return Optional.of(Example(id))
            }
            return Optional.empty()
        }
    }

    inner class Example(
        @field:Unique(repository = Repository::class)
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