package io.github.robertomike.jakidate.validations.web.ip

import io.github.robertomike.jakidate.constraints.web.IpConstraint
import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import kotlin.reflect.KClass

/**
 * Validates a string is an IPv4
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp = IpConstraint.IP4_REGEX, flags = [Pattern.Flag.CASE_INSENSITIVE], message = "{jakidate.web.ip.ipv4}")
annotation class Ipv4(
    /**
     * the error message template
     */
    val message: String = "{jakidate.web.ip.ipv4}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)
