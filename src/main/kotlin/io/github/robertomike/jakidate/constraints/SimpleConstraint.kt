package io.github.robertomike.jakidate.constraints

import io.github.robertomike.jakidate.exceptions.RulesException
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.lang.reflect.InvocationTargetException

@FunctionalInterface
abstract class SimpleConstraint<A: Annotation, T>: ConstraintValidator<A, T> {
    protected lateinit var annotation: A

    override fun initialize(constrain: A) {
        annotation = constrain
    }

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

    open fun isValid(value: T): Boolean {
        TODO()
    }
}