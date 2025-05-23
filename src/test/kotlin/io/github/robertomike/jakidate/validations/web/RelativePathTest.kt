package io.github.robertomike.jakidate.validations.web

import io.github.robertomike.jakidate.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RelativePathTest : BaseTest() {
    inner class Example(
        @field:RelativePath
        val value: String,
    )

    @ParameterizedTest
    @ValueSource(strings = ["/this/is/a/path", "/", "/th!s/1s/a/path"])
    fun good(value: String, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @ValueSource(strings = ["wrong/path", "", "/cia/"])
    fun error(value: String, validator: Validator) {
        val example = Example(value)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
        checkMessages(constraints)
    }
}