package io.github.robertomike.springrules.constraints.ip

import io.github.robertomike.springrules.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Ip4Validation

class Ip4Constraint : SimpleConstraint<Ip4Validation, String> {
    override fun isValid(value: String): Boolean {
        return IpConstraint.ipv4Regex.matches(value)
    }
}