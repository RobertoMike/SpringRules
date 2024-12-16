package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.objects.conditionals.*

class ExcludeConstraint: SimpleMessageConstraint<Exclude, Any>() {
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val validateWithConditional = ValidateWithConditional(
            value, util, annotation.checkEmpty, true
        )

        return validateWithConditional.isValid(ExcludeIf::class.java, "exclude.if") &&
                validateWithConditional.isValid(ExcludeUnless::class.java, "exclude.unless", true)
    }
}