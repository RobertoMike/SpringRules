package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import io.github.robertomike.jakidate.validations.objects.conditionals.CustomExpressionTest.PersonTypeEnum

class CustomExpression : Expression<PersonTypeEnum>() {
    override fun apply(value: PersonTypeEnum, unless: Boolean): Boolean {
        return (PersonTypeEnum.NATURAL != value) == !unless
    }
}