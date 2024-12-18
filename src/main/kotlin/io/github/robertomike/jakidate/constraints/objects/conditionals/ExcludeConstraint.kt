package io.github.robertomike.jakidate.constraints.objects.conditionals

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.objects.conditionals.Exclude
import io.github.robertomike.jakidate.validations.objects.conditionals.ExcludeIf
import io.github.robertomike.jakidate.validations.objects.conditionals.ExcludeUnless

class ExcludeConstraint : SimpleMessageConstraint<Exclude, Any>() {
    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
        val validateWithConditional = ValidateWithConditional(
            value, util, annotation.checkEmpty, true
        )


        val ifValid = validateWithConditional.isValid(ExcludeIf::class.java, "exclude.if")
        val unlessValid = validateWithConditional.isValid(ExcludeUnless::class.java, "exclude.unless", true)

        return ifValid && unlessValid
    }
}