package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.BaseTest
import javax.validation.Validator
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.web.multipart.MultipartFile
import java.util.*

class ExtensionTest : BaseTest() {
    inner class Example(
        @field:Extension(["jpg", "jpeg", "png"])
        var file: MultipartFile
    )

    @Test
    fun valid(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.originalFilename).thenReturn("profile.jpg")

        val example = Example(file)

        val errors = validator.validate(example)

        assert(errors.isEmpty())
    }

    @Test
    fun notValid(validator: Validator) {
        val file = Mockito.mock(MultipartFile::class.java)
        Mockito.`when`(file.originalFilename).thenReturn("profile.webp")

        val example = Example(file)

        val errors = validator.validate(example)

        checkMessages(errors)
    }
}