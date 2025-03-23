package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExcludeTest : BaseTest() {
    @Exclude
    inner class Example(
        @field:Conditional val control: Boolean,
        @field:ExcludeIf val password: String?,
        @field:Conditional("email") val condition: String,
        @field:ExcludeUnless(key = "email") val email: String?,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            true,
            "",
            "false",
            "",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun allError(validator: Validator) {
        val example = Example(
            true,
            "someRandomPassword",
            "n",
            "email@mail.com",
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
            false,
            null,
            "0",
            "email@mail.com",
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }

    @Exclude(checkEmpty = false)
    inner class CheckEmptyFalseExample(
        @field:Conditional val control: Boolean,
        @field:ExcludeIf val password: String?,
        @field:Conditional("email") val condition: String,
        @field:ExcludeUnless(key = "email") val email: String?,
    )

    @Test
    fun checkEmptySetToFalseGood(validator: Validator) {
        val example = CheckEmptyFalseExample(
            true,
            null, //This should pass.
            "false",
            "", //This should not pass when checkEmpty is set to false, because it's not null.
        )

        val constraints = validator.validate(example)

        assertEquals(1, constraints.size)
        assertEquals("email", constraints.first().propertyPath.first().name)
    }

    @Exclude
    inner class DifferentTypesExample(
        @field:Conditional val control: Boolean,
        @field:ExcludeIf val firstName: String?,
        @field:ExcludeIf val lastName: String?,
        @field:Conditional("excludeUnless") val excludeUnlessCondition: String,
        @field:ExcludeUnless(key = "excludeUnless") val isPremiumSubscriber: Boolean?,
        @field:ExcludeUnless(key = "excludeUnless") val age: Number?,
        @field:ExcludeUnless(key = "excludeUnless") val email: String?,
    )

    @Test
    fun differentTypesGood(validator: Validator) {
        val differentTypesExample = DifferentTypesExample(
            true,
            "",
            "",
            "true",
            true,
            18,
            "example@gmail.com",
        )

        val constraints = validator.validate(differentTypesExample)

        assert(constraints.isEmpty())
    }

    @Exclude()
    inner class DifferentTypesAndConditionsExample(
        @field:Conditional val control: Boolean,
        @field:ExcludeIf val firstName: String?,
        @field:Conditional("isPremiumSubscriber") val isPremiumSubscriberCondition: String,
        @field:ExcludeUnless(key = "isPremiumSubscriber") val isPremiumSubscriber: Boolean?,
        @field:Conditional("age") val ageCondition: String,
        @field:ExcludeUnless(key = "age") val age: Number?,
        @field:Conditional("email") val emailCondition: String,
        @field:ExcludeUnless(key = "email") val email: String?,
    )

    @Test
    fun differentTypesAndConditionsExampleGood(validator: Validator) {
        val differentTypesAndConditionsExample = DifferentTypesAndConditionsExample(
            true,
            "",
            "true",
            true,
            "1",
            18,
            "true",
            "example@gmail.com",
        )

        val constraints = validator.validate(differentTypesAndConditionsExample)

        assert(constraints.isEmpty())
    }
}