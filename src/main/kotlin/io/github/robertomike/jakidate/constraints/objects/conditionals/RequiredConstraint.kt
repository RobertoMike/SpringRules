package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.objects.conditionals.Required
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredIf
import io.github.robertomike.jakidate.validations.objects.conditionals.RequiredUnless

class RequiredConstraint: SimpleMessageConstraint<Required, Any>() {
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val validateWithConditional = ValidateWithConditional(
            value, util, annotation.checkEmpty
        )

        return validateWithConditional.isValid(RequiredIf::class.java, "required.if") &&
                validateWithConditional.isValid(RequiredUnless::class.java, "required.unless", true)
    }
}