package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import javax.validation.ConstraintValidatorContext
import java.lang.reflect.InvocationTargetException

/**
 * A simple constraint validator that provides a basic implementation of the
 * ConstraintValidator interface with a message util to manage messages.
 *
 * @author Robert Micheletti
 * @since 1.0.0
 *
 * @param A the type of the annotation
 * @param T the type of the value to be validated
 */
abstract class SimpleMessageConstraint<A: Annotation, T>: SimpleConstraint<A, T>() {
    /**
     * Checks if the given value is valid.
     *
     * @param value the value to be validated
     * @param context the constraint validator context
     * @return true if the value is valid, false otherwise
     *
     * @throws RulesException if there is an error retrieving the method or invoking some method
     */
    override fun isValid(value: T?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        try {
            return isValid(value, MessageUtil(context))
        } catch (e: NoSuchMethodException) {
            throw RulesException("There was an error retrieving the method", e)
        } catch (e: InvocationTargetException) {
            throw RulesException("There was an error invoking some method", e)
        }
    }

    /**
     * Checks if the given value is valid.
     *
     * @param value the value to be validated
     * @param util the message util
     * @return true if the value is valid, false otherwise
     */
    abstract fun isValid(value: T, util: MessageUtil): Boolean
}