package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomExpressionTest : BaseTest() {
    enum class PersonTypeEnum {
        NATURAL, LEGAL_PERSON
    }

    @Exclude
    @Required
    inner class Example(
        @field:Conditional(expression = CustomExpression::class)
        val personType: PersonTypeEnum,
        @field:ExcludeIf // control != PersonTypeEnum.NATURAL
        @field:RequiredUnless
        val fiscalCode: String?,
        @field:ExcludeUnless // control != PersonTypeEnum.LEGAL_PERSON
        @field:RequiredIf
        val pIva: String?
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            PersonTypeEnum.NATURAL,
            "Ciao",
            ""
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun goodLegal(validator: Validator) {
        val example = Example(
            PersonTypeEnum.LEGAL_PERSON,
            "",
            "Ciao"
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example(
            PersonTypeEnum.LEGAL_PERSON,
            "Ciao",
            "Ciao"
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("fiscalCode", constraints.first().propertyPath.first().name)
    }
}