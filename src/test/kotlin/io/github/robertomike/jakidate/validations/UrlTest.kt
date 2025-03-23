package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.web.Url
import javax.validation.Validator
import org.junit.jupiter.api.Test

class UrlTest : BaseTest() {
    inner class Example(
        @field:Url
        val url: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            "https://google.com",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example(
            "some random thing",
        )

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}