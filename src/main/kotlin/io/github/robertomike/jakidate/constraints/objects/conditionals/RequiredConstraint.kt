package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.objects.conditionals.Required
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredUnless

/**
 * A constraint that checks if a value is required based on certain conditions.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class RequiredConstraint: SimpleMessageConstraint<Required, Any>() {
    /**
     * Checks if the given value is valid based on the required conditions.
     *
     * @param value the value to be validated
     * @param util the message utility to be used for validation
     * @return true if the value is valid, false otherwise
     */
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val validateWithConditional = ValidateWithConditional(
            value, util, annotation.checkEmpty
        )

        val ifValid = validateWithConditional.isValid(RequiredIf::class.java, "required.if")
        val unlessValid = validateWithConditional.isValid(RequiredUnless::class.java, "required.unless", true)

        return ifValid && unlessValid
    }
}