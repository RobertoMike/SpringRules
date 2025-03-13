package io.github.robertomike.jakidate.validations.passwords

import io.github.robertomike.jakidate.constraints.passwords.PasswordConstraint
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for validating passwords.
 *
 * This annotation is used to validate passwords based on various criteria such as length, digit, letters, uppercase and lowercase letters, and special characters.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraint::class])
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
annotation class Password(
    /**
     * the error message template
     */
    val message: String = "{jakidate.password.default}",/**
     * The minimum length of the password.
     *
     * This is the minimum number of characters that the password must have to be considered valid.
     *
     * the minimum length of the password
     */
    val minLength: Int = 8,

    /**
     * The maximum length of the password.
     *
     * This is the maximum number of characters that the password can have to be considered valid.
     *
     * the maximum length of the password
     */
    val maxLength: Int = 256,

    /**
     * Whether the password must contain digits.
     *
     * If this is true, the password must contain at least one digit to be considered valid.
     *
     * whether the password must contain digits
     */
    val digit: Boolean = true,

    /**
     * Whether the password must contain letters.
     *
     * If this is true, the password must contain at least one letter to be considered valid.
     *
     * whether the password must contain letters
     */
    val letters: Boolean = true,

    /**
     * Whether the password must contain both uppercase and lowercase letters.
     *
     * If this is true, the password must contain at least one uppercase letter and one lowercase letter to be considered valid.
     *
     * whether the password must contain both uppercase and lowercase letters
     */
    val uppercaseAndLowercase: Boolean = true,

    /**
     * Whether the password must contain special characters.
     *
     * If this is true, the password must contain at least one special character to be considered valid.
     *
     * whether the password must contain special characters
     */
    val specialCharacters: Boolean = true,
    /**
     * the groups the constraint belongs to
     */
    val groups: Array<KClass<*>> = [],
    /**
     * the payload associated to the constraint
     */
    val payload: Array<KClass<out Payload>> = []
)