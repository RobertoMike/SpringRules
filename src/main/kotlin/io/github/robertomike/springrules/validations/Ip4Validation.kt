package io.github.robertomike.springrules.validations

import io.github.robertomike.springrules.constraints.IpConstraint
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
@Pattern(regexp = IpConstraint.IP4_REGEX, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{spring-rules.ip4}")
annotation class Ip4Validation
