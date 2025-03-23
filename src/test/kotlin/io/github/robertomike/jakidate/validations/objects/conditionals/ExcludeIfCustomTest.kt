package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExcludeIfCustomTest : BaseTest() {
    enum class CustomEnum {
        ONE, TWO, THREE
    }

    @Exclude
    inner class Example(
        @field:ExcludeIf(true, expression = CustomExpression::class)
        @field:ExcludeUnless(true, expression = CustomExpression::class)
        val control: CustomEnum,
        @field:ExcludeIf
        val password: String?,
        @field:ExcludeUnless
        val email: String?
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            CustomEnum.TWO,
            "Ciao"
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example(
            CustomEnum.ONE,
            "Ciao"
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("password", constraints.first().propertyPath.first().name)
    }
}