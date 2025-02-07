package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.Test

class PathTest : BaseTest() {
    inner class Example(
        @field:Path
        val value: String,
    )

    @Test
    fun good(validator: Validator) {
        val example = Example(
            "/this/is/a/path",
        )

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @Test
    fun error(validator: Validator) {
        val example = Example(
            "some random path/",
        )

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}