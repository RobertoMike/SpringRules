package io.github.robertomike.jakidate.validations.web.ip

import io.github.robertomike.jakidate.constraints.web.IpConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import jakarta.validation.constraints.Pattern
import jakarta.validation.ReportAsSingleViolation
import kotlin.reflect.KClass

/**
 * Validates a string is an IPv6
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = IpConstraint.IP6_REGEX, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{jakidate.ipv6}")
annotation class Ipv6(
    /**
     * the error message template
     */
    val message: String = "{jakidate.ipv6}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
