package io.github.robertomike.springrules.advice

import io.github.robertomike.jakidate.validations.Accepted
import io.github.robertomike.springrules.BaseTest
import io.github.robertomike.springrules.configs.SpringRulesConfig
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException
import javax.validation.Validator
import org.hibernate.validator.internal.engine.path.PathImpl
import org.junit.jupiter.api.Test
import org.mockito.Mockito
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

    @Test
    fun validWithParameter() {
        val errors = mutableSetOf<ConstraintViolation<*>>()
        val error = Mockito.mock(ConstraintViolation::class.java)
        val path = PathImpl.createRootPath()
        path.addPropertyNode("users")
        path.addParameterNode("users", 2)
        path.addPropertyNode("name")
        Mockito.`when`(error.propertyPath).thenReturn(path)
        Mockito.`when`(error.message).thenReturn("Super error")
        errors.add(error)

        val response = advise.validationError(ConstraintViolationException(errors))

        assertEquals(400, response.statusCode.value())

        val violations = response.body!!.violations

        assertTrue(violations.isNotEmpty())
        assertEquals("users[2].name", violations.first().field)
    }
}