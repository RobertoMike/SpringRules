package io.github.robertomike.jakidate.validations.objects.conditionals

import io.github.robertomike.jakidate.constraints.objects.Expression
import io.github.robertomike.jakidate.validations.objects.conditionals.ExcludeIfCustomTest.CustomEnum

class CustomExpression : Expression<CustomEnum>() {
    override fun apply(value: CustomEnum, unless: Boolean): Boolean {
        return (CustomEnum.ONE == value) == !unless
    }
}