package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.BaseTest
import jakarta.validation.Validator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.context.ApplicationContext
import org.springframework.util.unit.DataUnit
import org.springframework.web.multipart.MultipartFile
import java.util.*

class FileSizeTest : BaseTest() {
    inner class Example(
        @field:FileSize(min = 1, max = 10)
        var file: MultipartFile
    )
    inner class ExampleByte(
        @field:FileSize(min = 1, max = 100, typeUnit = DataUnit.BYTES)
        var file: MultipartFile
    )

    @Test
    fun valid(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.size).thenReturn(2048)

        val example = Example(file)

        val errors = validator.validate(example)

        assert(errors.isEmpty())
    }

    @Test
    fun notValidMax(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.size).thenReturn(12000)

        val example = Example(file)

        val errors = validator.validate(example)

        checkMessages(errors)
    }

    @Test
    fun notValidMin(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.size).thenReturn(480)

        val example = Example(file)

        val errors = validator.validate(example)

        checkMessages(errors)
    }

    @Test
    fun notValidMaxByte(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.size).thenReturn(150)

        val example = ExampleByte(file)

        val errors = validator.validate(example)

        checkMessages(errors)
    }
}