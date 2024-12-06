package io.github.robertomike.springrules.constraints.ip

import io.github.robertomike.springrules.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Ip6Validation

class Ip6Constraint : SimpleConstraint<Ip6Validation, String> {
    override fun isValid(value: String): Boolean {
        return IpConstraint.ipv6Regex.matches(value)
    }
}