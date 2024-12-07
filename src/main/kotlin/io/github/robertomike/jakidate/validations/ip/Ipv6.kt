package io.github.robertomike.jakidate.validations.ip

import io.github.robertomike.jakidate.constraints.IpConstraint
import jakarta.validation.constraints.Pattern

@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Pattern(regexp = IpConstraint.IP6_REGEX, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{spring-rules.ip6}")
annotation class Ipv6
