package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.context.ApplicationContext

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

    @BeforeEach
    fun setUp(context: ApplicationContext) {
        Mockito.`when`(context.getBean(Repository::class.java)).thenReturn(Repository())
    }

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

        checkMessages(errors)
    }
}