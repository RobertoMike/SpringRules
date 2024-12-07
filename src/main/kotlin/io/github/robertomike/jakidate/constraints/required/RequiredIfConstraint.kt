package io.github.robertomike.jakidate.constraints.required

import io.github.robertomike.jakidate.constraints.SimpleMessageConstraint
import io.github.robertomike.jakidate.exceptions.RulesException
import io.github.robertomike.jakidate.utils.MessageUtil
import io.github.robertomike.jakidate.validations.`object`.required.RequiredIf
import io.github.robertomike.jakidate.validations.`object`.required.RequiredIfField
import java.lang.reflect.Field

class RequiredIfConstraint : SimpleMessageConstraint<RequiredIf, Any>() {
    private val annotationField = RequiredIfField::class.java

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        val fields = getFields(value)

        return fields.all { (_, list) -> validateGroup(list, util, value) }
    }

    private fun getFields(value: Any): Map<String, List<Field>> {
        val fields = value.javaClass.declaredFields
            .asSequence()
            .filter { it.isAnnotationPresent(annotationField) }
            .groupBy { it.getAnnotation(annotationField).key }
            .toMap()

        if (fields.isEmpty()) {
            throw RulesException("You need to use the @RequiredIfField annotation on the condition field and necessary field")
        }

        return fields
    }

    private fun validateGroup(fields: List<Field>, util: MessageUtil, original: Any): Boolean {
        val fieldCondition = fields.firstOrNull { it.getAnnotation(annotationField).value }
            ?: throw RulesException("You need to use the @RequiredIfField annotation on the condition field")

        val annotationCondition = fieldCondition.getAnnotation(annotationField)
        val value = getValue(fieldCondition, original) ?:
        throw RulesException("The value of the field cannot be null when is condition field")

        val fieldsToValidate = fields.filter { !it.getAnnotation(annotationField).value }

        if (value is Boolean && value) {
            return validateFields(fieldsToValidate, original, util)
        }

        val lambda = annotationCondition.expression.members.single { it.name == "apply" }

        if (lambda.call(value) as Boolean) {
            return validateFields(fieldsToValidate, original, util)
        }

        return true
    }

    private fun validateFields(fields: List<Field>, original: Any, util: MessageUtil): Boolean {
        return fields.all { field ->
            if (getValue(field, original) == null) {
                // TODO how can we add the parameters for the message?
                util.addMessage("required-if.field-null")
                return false
            }

            true
        }
    }

    private fun getValue(field: Field, original: Any): Any? {
        field.trySetAccessible()

        return field.get(original)
    }
}