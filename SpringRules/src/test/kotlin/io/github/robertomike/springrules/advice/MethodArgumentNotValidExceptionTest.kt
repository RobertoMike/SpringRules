package io.github.robertomike.springrules.advice

import io.github.robertomike.springrules.BaseTest
import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.configs.ViolationType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.core.MethodParameter
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MethodArgumentNotValidExceptionTest : BaseTest() {
    private val advise: MethodArgumentNotValidExceptionAdvice
    private val properties = SpringRulesConfig()

    init {
        properties.violationBody = ViolationType.SINGLE_MESSAGE
        advise = MethodArgumentNotValidExceptionAdvice(properties)
    }

    @Test
    fun valid() {
        val methodParam = Mockito.mock(MethodParameter::class.java)
        val bindResult = Mockito.mock(BindingResult::class.java)
        val fieldError = FieldError("request", "name", "notNull")
        val objectError = ObjectError("request", "cannot be null")

        Mockito.`when`(bindResult.globalErrors).thenReturn(listOf(objectError))
        Mockito.`when`(bindResult.fieldErrors).thenReturn(listOf(fieldError))

        val exception = MethodArgumentNotValidException(methodParam, bindResult)

        val response = advise.onMethodArgumentNotValidException(exception)

        assertEquals(422, response.statusCode.value())

        val violations = response.body!!.violations

        assertTrue(violations.isNotEmpty())
        assertNotNull(violations.firstOrNull { it.field == "name" })
        assertNotNull(violations.firstOrNull { it.field == "form" })
    }
}