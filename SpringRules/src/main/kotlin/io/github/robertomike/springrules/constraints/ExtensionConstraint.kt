package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.Extension
import org.springframework.web.multipart.MultipartFile
import java.util.*

class ExtensionConstraint: SimpleConstraint<Extension, MultipartFile>() {
    override fun isValid(value: MultipartFile): Boolean {
        val fileName = Objects.requireNonNull(value.originalFilename)

        return annotation.value
            .asSequence()
            .map { ".$it" }
            .any(fileName::endsWith)
    }
}