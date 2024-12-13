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
import kotlin.reflect.full.createInstance

class RequiredConstraint: SimpleMessageConstraint<Required, Any>() {
    private val annotationField = RequiredIf::class.java

    override fun isValid(value: Any, util: MessageUtil): Boolean {
        util.resetDefaultMessage()
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
        val fieldCondition = fields.firstOrNull { it.getAnnotation(annotationField).conditional }
            ?: throw RulesException("You need to use the @RequiredIf annotation on the condition field")

        val annotationCondition = fieldCondition.getAnnotation(annotationField)
        val value = fieldCondition.getValue(original)
            ?: throw RulesException("The value of the field cannot be null when is condition field")

        val fieldsToValidate = fields.filter { !it.getAnnotation(annotationField).conditional }

        if (value is Boolean) {
            if (!value) {
                return true
            }

            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, original, util)
        }

        val expression = annotationCondition.expression.createInstance()

        if (expression.apply(value)) {
            util.addParameters(Pair("conditionField", fieldCondition.name))
            return validateFields(fieldsToValidate, original, util)
        }

        return true
    }

    private fun validateFields(fields: List<Field>, original: Any, util: MessageUtil): Boolean {
        return fields.all { field ->
            val value = field.getValue(original)
            val isEmpty = checkIfEmpty(value)
            if (value == null || isEmpty) {
                util.addMessageForProperty(field, "required-if.field-null")
                return false
            }

            true
        }
    }

    private fun checkIfEmpty(value: Any?): Boolean {
        if (value == null || !annotation.checkEmpty) {
            return false
        }

        return when (value) {
            is String -> value.isEmpty()
            is Collection<*> -> value.isEmpty()
            else -> false
        }
    }
}