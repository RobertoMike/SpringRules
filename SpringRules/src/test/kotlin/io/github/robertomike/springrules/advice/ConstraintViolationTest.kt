package io.github.robertomike.springrules.advice

import io.github.robertomike.jakidate.validations.booleans.Accepted
import io.github.robertomike.springrules.BaseTest
import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.ViolationsBySubFields
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validator
import org.hibernate.validator.internal.engine.path.MutablePath
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
        val path = MutablePath.createRootPath()
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
        createAndAddError(errors, "name", 2)
        createAndAddError(errors, "lastName", 1)
        createAndAddError(errors, "name", 1)

        val response = advise.validationError(ConstraintViolationException(errors))

        assertEquals(400, response.statusCode.value())

        val violations = response.body!!.violations as MutableList<ViolationsBySubFields>

        assertTrue(violations.isNotEmpty())

        val generatedViolation = ViolationsBySubFields("users", mutableListOf("[2]", "name"), messageError)
        generatedViolation.addSubField(mutableListOf("[1]", "name"), messageError)
        generatedViolation.addSubField(mutableListOf("[1]", "lastName"), messageError)

        // assertEquals(generatedViolation, violations.first())
    }

    private fun createAndAddError(errors: MutableSet<ConstraintViolation<*>>, el: String, index: Int) {
        val error = Mockito.mock(ConstraintViolation::class.java)
        val path = MutablePath.createRootPath()
        path.addPropertyNode("users")
        path.addParameterNode("users", index)
        path.addPropertyNode(el)
        Mockito.`when`(error.propertyPath).thenReturn(path)
        Mockito.`when`(error.message).thenReturn(messageError)
        errors.add(error)
    }

    @Test
    fun `a class-level (bean node only) path resolves to an empty field path instead of throwing`() {
        val error = Mockito.mock(ConstraintViolation::class.java)
        val path = MutablePath.createRootPath()
        path.addBeanNode()
        Mockito.`when`(error.propertyPath).thenReturn(path)

        val fieldPath = advise.getPropertyPath(path)

        assertTrue(fieldPath.isEmpty())
    }

    @Test
    fun `a cross-parameter path is ignored rather than mistaken for a regular parameter`() {
        val path = MutablePath.createRootPath()
        path.addPropertyNode("transfer")
        path.addCrossParameterNode()

        val fieldPath = advise.getPropertyPath(path)

        assertEquals(mutableListOf("transfer"), fieldPath)
    }

    @Test
    fun `a lone parameter node (no leading property) resolves using its index`() {
        val path = MutablePath.createRootPath()
        path.addParameterNode("amount", 0)

        val fieldPath = advise.getPropertyPath(path)

        assertEquals(mutableListOf("[0]"), fieldPath)
    }

    @Test
    fun `an element inside a @Valid List keeps its numeric index in the field path`() {
        val path = MutablePath.createRootPath()
        path.addPropertyNode("items")
        path.addContainerElementNode("<list element>")
        path.makeLeafNodeIterableAndSetIndex(3)
        path.addPropertyNode("name")

        val fieldPath = advise.getPropertyPath(path)

        assertEquals(mutableListOf("items", "[3]", "name"), fieldPath)
    }

    inner class ListHolder(
        @field:jakarta.validation.Valid
        var items: MutableList<Example> = mutableListOf(Example(false))
    )

    @Test
    fun `a real cascaded List element failure reports its index, not just the field name`(validator: Validator) {
        val errors = validator.validate(ListHolder())

        val response = advise.validationError(ConstraintViolationException(errors))
        val violations = response.body!!.violations as MutableList<ViolationsBySubFields>

        assertEquals("items", violations.first().field)
        assertEquals("[0]", violations.first().subfields!!.first().field)
        assertEquals("value", violations.first().subfields!!.first().subfields!!.first().field)
    }

    @Test
    fun `an element inside a @Valid Map keeps its key in the field path`() {
        val path = MutablePath.createRootPath()
        path.addPropertyNode("items")
        path.addContainerElementNode("<map value>")
        path.makeLeafNodeIterableAndSetMapKey("europe")
        path.addPropertyNode("name")

        val fieldPath = advise.getPropertyPath(path)

        assertEquals(mutableListOf("items", "[europe]", "name"), fieldPath)
    }
}