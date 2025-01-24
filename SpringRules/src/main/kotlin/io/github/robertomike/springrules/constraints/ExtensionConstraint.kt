package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Extension
import org.springframework.web.multipart.MultipartFile

/**
 * A constraint that validates the extension of a multipart file.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class ExtensionConstraint: SimpleConstraint<Extension, MultipartFile>() {
    /**
     * Checks if the file extension is valid.
     *
     * @param value the multipart file to validate
     * @return true if the file extension is valid, false otherwise
     */
    override fun isValid(value: MultipartFile): Boolean {
        val fileName = value.originalFilename ?: throw NullPointerException("File name cannot be null")

        return annotation.value
            .asSequence()
            .map { ".$it" }
            .any(fileName::endsWith)
    }
}