package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RequiredTest : BaseTest() {
    @Test
    fun good(validator: Validator) {
        val example = Example(
            "yes",
            "1234",
            "no",
            "user@mail.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            "yes",
            null,
            "false",
            "",
        )

        val constraints = validator.validate(example)

        assertEquals(2, constraints.size)
        assert(constraints.any { it.propertyPath.first().name == "password" })
        assert(constraints.any { it.propertyPath.first().name == "email" })
        checkMessages(constraints)
    }

    @Test
    fun onlyOneError(validator: Validator) {
        val example = Example(
            "no",
            null,
            "off",
            null,
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }

    @Required
    inner class Example(
        @field:RequiredIf(true)
        val control: String,
        @field:RequiredIf
        val password: String?,
        @field:RequiredUnless(true, "email")
        val condition: String,
        @field:RequiredUnless(key = "email")
        val email: String?,
    )

    @Required(checkEmpty = false)
    inner class CheckEmptyFalseExample(
        @field:RequiredIf(true) val control: Boolean,
        @field:RequiredIf val password: String?,
        @field:RequiredUnless(true, "email") val condition: String,
        @field:RequiredUnless(key = "email") val email: String?,
    )

    @Test
    fun checkEmptySetToFalseGood(validator: Validator) {
        val example = CheckEmptyFalseExample(
            true,
            "", //This should pass when checkEmpty is set to false, because it's not null.
            "false",
            null, //This should not pass because it's null.
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }

    @Required
    inner class DifferentTypesExample(
        @field:RequiredIf(true) val control: Boolean,
        @field:RequiredIf val firstName: String?,
        @field:RequiredIf val lastName: String?,
        @field:RequiredUnless(true, "excludeUnless") val excludeUnlessCondition: String,
        @field:RequiredUnless(key = "excludeUnless") val isPremiumSubscriber: Boolean?,
        @field:RequiredUnless(key = "excludeUnless") val age: Number?,
        @field:RequiredUnless(key = "excludeUnless") val email: String?,
    )

    @Test
    fun differentTypesGood(validator: Validator) {
        val differentTypesExample = DifferentTypesExample(
            true,
            "John",
            "Doe",
            "true",
            false,
            null,
            null,
        )

        val constraints = validator.validate(differentTypesExample)

        assert(constraints.isEmpty())
    }

    @Required()
    inner class DifferentTypesAndConditionsExample(
        @field:RequiredIf(true) val control: Boolean,
        @field:RequiredIf val firstName: String?,
        @field:RequiredUnless(true, "isPremiumSubscriber") val isPremiumSubscriberCondition: String,
        @field:RequiredUnless(key = "isPremiumSubscriber") val isPremiumSubscriber: Boolean?,
        @field:RequiredUnless(true, "age") val ageCondition: String,
        @field:RequiredUnless(key = "age") val age: Number?,
        @field:RequiredUnless(true, "email") val emailCondition: String,
        @field:RequiredUnless(key = "email") val email: String?,
    )

    @Test
    fun differentTypesAndConditionsExampleGood(validator: Validator) {
        val differentTypesAndConditionsExample = DifferentTypesAndConditionsExample(
            true,
            "John",
            "true",
            false,
            "1",
            null,
            "true",
            null,
        )

        val constraints = validator.validate(differentTypesAndConditionsExample)

        assert(constraints.isEmpty())
    }
}