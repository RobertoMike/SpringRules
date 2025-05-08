package io.github.robertomike.jakidate.validations.strings.nullable

import io.github.robertomike.jakidate.utils.YamlFileSource
import io.github.robertomike.jakidate.validations.YamlTest
import javax.validation.Validator
import org.junit.jupiter.api.Test

@YamlFileSource("/strings/nullable/nullOrNotEmpty")
class NullOrNotEmptyTest : YamlTest() {
    inner class Example(
        @field:NullOrNotEmpty
        val value: String? = null
    )

    override fun getExample(value: String): Any {
        return Example(value)
    }

    @Test
    fun getExampleWithoutValue(validator: Validator) {
        val example = Example()

        val result = validator.validate(example)

        assert(result.isEmpty())
    }
}
