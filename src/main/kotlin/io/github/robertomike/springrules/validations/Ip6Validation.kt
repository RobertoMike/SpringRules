package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.IpConstraint
import jakarta.validation.constraints.Pattern

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Pattern(regexp = IpConstraint.IP6_REGEX, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{spring-rules.ip6}")
annotation class Ip6Validation
