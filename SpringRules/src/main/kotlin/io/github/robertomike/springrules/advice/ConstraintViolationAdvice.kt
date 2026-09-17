package io.github.robertomike.springrules.advice

import io.github.robertomike.springrules.configs.SpringRulesConfig
import io.github.robertomike.springrules.responses.Violations
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ElementKind
import jakarta.validation.Path
import jakarta.validation.ValidationException
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
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
@ConditionalOnClass(name = ["org.hibernate.validator.HibernateValidator"])
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

        var method = false

        path.forEach {
            when (it.kind) {
                ElementKind.PROPERTY -> {
                    addIterableMarker(finalPath, it)
                    finalPath.add(it.name)
                }
                ElementKind.METHOD -> method = true
                ElementKind.PARAMETER -> {
                    if (method) {
                        finalPath.add(it.name)
                        return@forEach
                    }

                    if (it is Path.ParameterNode) {
                        finalPath.add("[${it.parameterIndex}]")
                    }
                }
                // Some providers/generic shapes carry the element index/key on a dedicated
                // CONTAINER_ELEMENT node instead of on the following PROPERTY node.
                ElementKind.CONTAINER_ELEMENT -> addIterableMarker(finalPath, it)
                else -> {}
            }
        }

        return finalPath
    }

    /**
     * Appends an `[index]`/`[key]` segment when [node] represents an element of a @Valid
     * List/Set/Map (i.e. [Path.Node.isInIterable] is true), so the failing element can be
     * identified in the resulting field path instead of being silently collapsed.
     */
    private fun addIterableMarker(finalPath: MutableList<String>, node: Path.Node) {
        if (!node.isInIterable) return

        if (node.index != null) {
            finalPath.add("[${node.index}]")
        } else if (node.key != null) {
            finalPath.add("[${node.key}]")
        }
    }
}
