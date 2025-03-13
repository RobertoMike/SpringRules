package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.passwords.NotCompromisedPassword
import javax.validation.Validator
import org.junit.jupiter.api.Test

class NotCompromisedPasswordTest : BaseTest() {
    inner class Example(
        @field:NotCompromisedPassword
        val password: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example("sup3r*str0ng/ultra__SECURED_p4SSw0rd")

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example("12345678")

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}