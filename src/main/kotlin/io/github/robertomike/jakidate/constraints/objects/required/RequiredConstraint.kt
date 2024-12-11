package io.github.robertomike.jakidate.constraints.objects.required

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.utils.getFieldsByAnnotation
import io.github.robertomike.jakidate.utils.getValue
import io.github.robertomike.jakidate.utils.validate
import io.github.robertomike.jakidate.validations.objects.required.Required
import io.github.robertomike.jakidate.validations.objects.required.RequiredIf
import java.lang.reflect.Field

class RequiredConstraint: SimpleMessageConstraint<Required, Any>() {
    private val annotationField = RequiredIf::class.java

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        val fields = getFields(value)

        return fields.validate { validateGroup(it, util, value) }
    }

    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = value.getFieldsByAnnotation(annotationField)
            .groupBy { it.getAnnotation(annotationField).key }

        if (fields.isEmpty()) {
            throw RulesException("You need to use the @RequiredIfField annotation on the condition field and necessary field")
        }

        return fields
    }

    private fun validateGroup(fields: List<Field>, util: MessageUtil, original: Any): Boolean {
        val fieldCondition = fields.firstOrNull { it.getAnnotation(annotationField).value }
            ?: throw RulesException("You need to use the @RequiredIf annotation on the condition field")

        val annotationCondition = fieldCondition.getAnnotation(annotationField)
        val value = fieldCondition.getValue(original)
            ?: throw RulesException("The value of the field cannot be null when is condition field")

        val fieldsToValidate = fields.filter { !it.getAnnotation(annotationField).value }

        if (value is Boolean && value) {
            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, original, util)
        }

        val lambda = annotationCondition.expression.members.single { it.name == "apply" }

        if (lambda.call(value) as Boolean) {
            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, original, util)
        }

        return true
    }

    private fun validateFields(fields: List<Field>, original: Any, util: MessageUtil): Boolean {
        return fields.all { field ->
            if (field.getValue(original) == null) {
                util.resetDefaultMessage()
                // TODO how can we add the parameters for the message?
                util.addMessageForProperty(field, "required-if.field-null")
                return false
            }

            true
        }
    }
}