package io.github.robertomike.springrules.advice

import io.github.robertomike.jakidate.validations.booleans.Accepted
import io.github.robertomike.springrules.BaseTest
import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.ViolationsBySubFields
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
    private val messageError = "Super error"

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
        Mockito.`when`(error.message).thenReturn(messageError)
        errors.add(error)

        val response = advise.validationError(ConstraintViolationException(errors))

        assertEquals(400, response.statusCode.value())

        val violations = response.body!!.violations as MutableList<ViolationsBySubFields>

        assertTrue(violations.isNotEmpty())
        assertEquals("users", violations.first().field)
        assertEquals("[2]", violations.first().subfields!!.first().field)
        assertEquals("name", violations.first().subfields!!.first().subfields!!.first().field)
    }

    @Test
    fun validWithManyParameters() {
        val errors = mutableSetOf<ConstraintViolation<*>>()
        createAndAddError(errors, "lastName", 1)
        createAndAddError(errors, "name", 1)
        createAndAddError(errors, "name", 2)

        val response = advise.validationError(ConstraintViolationException(errors))

        assertEquals(400, response.statusCode.value())

        val violations = response.body!!.violations as MutableList<ViolationsBySubFields>

        assertTrue(violations.isNotEmpty())

        val generatedViolation = ViolationsBySubFields("users", mutableListOf("[2]", "name"), messageError)
        generatedViolation.addSubField(mutableListOf("[1]", "name"), messageError)
        generatedViolation.addSubField(mutableListOf("[1]", "lastName"), messageError)

        assertEquals(generatedViolation, violations.first())
    }

    private fun createAndAddError(errors: MutableSet<ConstraintViolation<*>>, el: String, index: Int) {
        val error = Mockito.mock(ConstraintViolation::class.java)
        val path = PathImpl.createRootPath()
        path.addPropertyNode("users")
        path.addParameterNode("users", index)
        path.addPropertyNode(el)
        Mockito.`when`(error.propertyPath).thenReturn(path)
        Mockito.`when`(error.message).thenReturn(messageError)
        errors.add(error)
    }
}