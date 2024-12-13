package io.github.robertomike.jakidate.validations

import io.github.robertomike.jakidate.BaseTest
import io.github.robertomike.jakidate.validations.strings.ISSN
import jakarta.validation.Validator
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ISSNTest : BaseTest() {
    inner class Example(
        @field:ISSN
        val code: String
    )

    companion object {
        @JvmStatic
        fun valid(): Stream<String> {
            return loadAndGetYaml("/strings/issn")["good"]!!.stream()
        }
        @JvmStatic
        fun invalid(): Stream<String> {
            return loadAndGetYaml("/strings/issn")["wrong"]!!.stream()
        }
    }

    @ParameterizedTest
    @MethodSource("valid")
    fun good(issn: String, validator: Validator) {
        val example = Example(issn)

        val constraints = validator.validate(example)

        assert(constraints.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("invalid")
    fun wrong(issn: String, validator: Validator) {
        val example = Example(issn)

        val constraints = validator.validate(example)

        assert(constraints.isNotEmpty())
    }
}
