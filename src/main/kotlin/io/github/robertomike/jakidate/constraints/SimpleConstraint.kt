package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import java.lang.reflect.InvocationTargetException

/**
 * A simple constraint validator that provides a basic implementation of the
 * ConstraintValidator interface.
 *
 * @param <A> the type of annotation that this constraint validator supports
 * @param <T> the type of value that this constraint validator can validate
 * @author Roberto Micheletti
 * @since 1.0.0
 */
abstract class SimpleConstraint<A: Annotation, T>: ConstraintValidator<A, T> {
    /**
     * The annotation instance that is being validated.
     */
    protected lateinit var annotation: A

    /**
     * Initializes the constraint validator with the given annotation instance.
     *
     * @param constrain the annotation instance to initialize with
     */
    override fun initialize(constrain: A) {
        annotation = constrain
    }

    /**
     * Checks if the given value is valid according to the constraint.
     *
     * @param value the value to validate
     * @param context the constraint validation context
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: T?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        try {
            return isValid(value)
        } catch (e: NoSuchMethodException) {
            throw RulesException("There was an error retrieving the method", e)
        } catch (e: InvocationTargetException) {
            throw RulesException("There was an error invoking some method", e)
        }
    }

    /**
     * Checks if the given value is valid according to the constraint.
     *
     * This method is called by the isValid(T, ConstraintValidatorContext) method
     * and should be implemented by subclasses to provide the actual validation logic.
     *
     * @param value the value to validate
     * @return true if the value is valid, false otherwise
     */
    open fun isValid(value: T): Boolean {
        TODO()
    }
}