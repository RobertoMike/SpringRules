package io.github.robertomike.springrules.advice

import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.Violations
import javax.validation.ConstraintViolationException
import javax.validation.ValidationException
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Handles constraint violations in a Spring-based application.
 *
 * This class provides a centralized way to handle constraint violations that occur during validation.
 * It converts the constraint violations into a [Violations] object and returns it as a [ResponseEntity].
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@Configuration
@ControllerAdvice
@ConditionalOnProperty("spring-rules.controller-advice.method-argument-not-valid", matchIfMissing = true)
open class MethodArgumentNotValidExceptionAdvice(protected val config: SpringRulesConfig) {
    /**
     * Handles [MethodArgumentNotValidException] by converting it into a [Violations] and returning it as a [ResponseEntity].
     *
     * @param e the [ConstraintViolationException] to handle
     * @return a [ResponseEntity] containing a [Violations] with the violations of the [ValidationException] and the appropriate HTTP status code.
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun onMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Violations> {
        val errors = Violations()
        // These are errors that can happen when putting new error message on top the object
        e.bindingResult.globalErrors.forEach {
            errors.addError("form", it.defaultMessage ?: "", config.useSingleViolation)
        }

        e.bindingResult.fieldErrors.forEach {
            errors.addError(it.field, it.defaultMessage ?: "", config.useSingleViolation)
        }

        return ResponseEntity(errors, HttpStatus.UNPROCESSABLE_ENTITY)
    }
}
