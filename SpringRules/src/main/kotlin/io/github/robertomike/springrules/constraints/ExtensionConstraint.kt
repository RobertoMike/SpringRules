package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Extension
import org.springframework.web.multipart.MultipartFile

class ExtensionConstraint: SimpleConstraint<Extension, MultipartFile>() {
    override fun isValid(value: MultipartFile): Boolean {
        val fileName = value.originalFilename ?: throw NullPointerException("File name cannot be null")

        return annotation.value
            .asSequence()
            .map { ".$it" }
            .any(fileName::endsWith)
    }
}