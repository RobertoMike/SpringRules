package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.utils.getBeanAndExecute
import io.github.robertomike.springrules.utils.verifyIfPresent
import io.github.robertomike.springrules.validations.Unique
import org.springframework.context.ApplicationContext

/**
 * A constraint that checks if a value is unique in the database through the repository.
 *
 * This constraint uses the Spring Framework to execute a method on a repository
 * to verify if a value is unique.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class UniqueConstraint(private val applicationContext: ApplicationContext) : SimpleConstraint<Unique, Any>() {
    /**
     * The method to be executed on the repository to check for uniqueness.
     *
     * This method is specified in the [Unique] annotation.
     */
    private val method: String
        get() = annotation.method

    /**
     * The repository class that contains the method to be executed.
     *
     * This repository is specified in the [Unique] annotation.
     */
    private val repository: Class<out Any>
        get() = annotation.value.java

    /**
     * Checks if the given value is unique in the repository.
     *
     * This method executes the specified method on the repository with the given value
     * and returns true if the value is unique, false otherwise.
     *
     * @param value the value to be checked
     * @return true if the value is unique, false otherwise
     */
    override fun isValid(value: Any): Boolean {
        return !applicationContext.getBeanAndExecute(repository, method, value)
            .verifyIfPresent()
    }
}