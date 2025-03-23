package io.github.robertomike.springrules.advice

import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.Violations
import javax.validation.ConstraintViolationException
import javax.validation.ElementKind
import javax.validation.Path
import javax.validation.ValidationException
import org.hibernate.validator.internal.engine.path.NodeImpl
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
@ConditionalOnProperty("spring-rules.controller-advice.constraint-violations", matchIfMissing = true)
open class ConstraintViolationAdvice(protected val config: SpringRulesConfig) {
    /**
     * Handles [ConstraintViolationException] by converting it into a [Violations] and returning it as a [ResponseEntity].
     *
     * @param e the [ConstraintViolationException] to handle
     * @return a [ResponseEntity] containing a [Violations] with the violations of the [ValidationException] and the appropriate HTTP status code.
     */
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseBody
    fun validationError(e: ConstraintViolationException): ResponseEntity<Violations> {
        val errors = Violations()

        e.constraintViolations.forEach {
            errors.addError(getPropertyPath(it.propertyPath), it.message, config.violationBody)
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    /**
     * Gets the property path from a [Path] object.
     *
     * @param path the [Path] object to get the property path from
     * @return the property path as a string
     */
    open fun getPropertyPath(path: Path): MutableList<String> {
        val finalPath = mutableListOf<String>()

        path.forEach {
            when (it.kind) {
                ElementKind.PROPERTY -> finalPath.add(it.name)
                ElementKind.PARAMETER -> {
                    if (it.index != null) {
                        finalPath.add("[${it.index}]")
                    }

                    if (it is NodeImpl) {
                        finalPath.add("[${it.parameterIndex}]")
                    }
                }
                else -> {}
            }
        }

        return finalPath
    }
}
