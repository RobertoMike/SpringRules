package io.github.robertomike.springrules.validations.file

import io.github.robertomike.springrules.constraints.FileSizeConstraint
import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.springframework.util.unit.DataUnit
import kotlin.reflect.KClass

/**
 * This annotation is used to validate the size of a file
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [FileSizeConstraint::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.TYPE
)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class FileSize(
    /**
     * This parameter is used to define the max size that a file can have
     */
    val max: Long = Long.MAX_VALUE,
    /**
     * This parameter is used to define the min size that a file need to have
     */
    val min: Long = 0,
    /**
     * This parameter is used to define the type unit for the other two parameters
     */
    val typeUnit: DataUnit = DataUnit.KILOBYTES,
    /**
     * the error message template
     */
    val message: String = "{spring-rules.file.size}",
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated with the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)