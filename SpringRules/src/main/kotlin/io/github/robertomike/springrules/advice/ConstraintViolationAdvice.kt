package io.github.robertomike.springrules.advice

import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.Violations
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ElementKind
import jakarta.validation.Path
import jakarta.validation.ValidationException
import org.hibernate.validator.internal.engine.path.NodeImpl
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

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
            errors.addError(getPropertyPath(it.propertyPath), it.message, config.useSingleViolation)
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    private fun getPropertyPath(path: Path): String {
        val finalPath = StringBuilder()

        path.forEach {
            when (it.kind) {
                ElementKind.PROPERTY -> finalPath.append(it.name).append(".")
                ElementKind.PARAMETER -> {
                    if (it.index != null) {
                        finalPath.append("[").append(it.index).append("]").append(".")
                    }

                    if (it is NodeImpl) {
                        finalPath.append("[").append(it.parameterIndex).append("]").append(".")
                    }
                }
                else -> {}
            }
        }

        return finalPath.substring(0, finalPath.length - 1)
    }
}
