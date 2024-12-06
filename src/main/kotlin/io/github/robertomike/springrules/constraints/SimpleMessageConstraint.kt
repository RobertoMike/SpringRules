package io.github.robertomike.springrules.constraints

import io.github.robertomike.springrules.exceptions.SpringRulesException
import io.github.robertomike.springrules.utils.MessageUtil
import jakarta.validation.ConstraintValidatorContext
import java.lang.reflect.InvocationTargetException

@FunctionalInterface
abstract class SimpleMessageConstraint<A: Annotation, T>: SimpleConstraint<A, T>() {
    override fun isValid(value: T?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }

        try {
            return isValid(value, MessageUtil(context))
        } catch (e: NoSuchMethodException) {
            throw SpringRulesException("There was an error retrieving the method", e)
        } catch (e: InvocationTargetException) {
            throw SpringRulesException("There was an error invoking some method", e)
        }
    }

    abstract fun isValid(value: T, util: MessageUtil): Boolean
}