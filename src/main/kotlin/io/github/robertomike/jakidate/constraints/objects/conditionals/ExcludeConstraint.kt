package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.objects.conditionals.Exclude
import io.github.robertomike.jakidate.validations.objects.conditionals.ExcludeIf
import io.github.robertomike.jakidate.validations.objects.conditionals.ExcludeUnless

/**
 * A constraint that checks if a value is excluded based on certain conditions.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ExcludeConstraint : SimpleMessageConstraint<Exclude, Any>() {
    /**
     * Checks if the given value is valid based on the exclusion conditions.
     *
     * @param value the value to be validated
     * @param util the message utility to be used for validation
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val validateWithConditional = ValidateWithConditional(
            value, util, annotation.checkEmpty, true
        )


        val ifValid = validateWithConditional.isValid(ExcludeIf::class.java, "objects.conditionals.exclude.if")
        val unlessValid = validateWithConditional.isValid(ExcludeUnless::class.java, "objects.conditionals.exclude.unless", true)

        return ifValid && unlessValid
    }
}