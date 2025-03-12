package io.github.robertomike.springrules.advice

import io.github.robertomike.jakidate.validations.Accepted
import io.github.robertomike.springrules.BaseTest
import io.github.robertomike.springrules.configs.SpringRulesConfig
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConstraintViolationTest : BaseTest() {
    private val advise: ConstraintViolationAdvice
    private val properties = SpringRulesConfig()

    init {
        advise = ConstraintViolationAdvice(properties)
    }

    inner class Example(
        @field:Accepted
        var value: Boolean
    )

    @Test
    fun valid(validator: Validator) {
        val errors = validator.validate(Example(false))

        val response = advise.validationError(ConstraintViolationException(errors))

        assertEquals(400, response.statusCode.value())

        val violations = response.body!!.violations

        assertTrue(violations.isNotEmpty())
        assertEquals("value", violations.first().field)
    }
}