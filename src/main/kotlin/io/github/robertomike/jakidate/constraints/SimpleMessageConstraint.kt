package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import jakarta.validation.ConstraintValidatorContext
import java.lang.reflect.InvocationTargetException

abstract class SimpleMessageConstraint<A: Annotation, T>: SimpleConstraint<A, T>() {
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

    abstract fun isValid(value: T, util: MessageUtil): Boolean
}