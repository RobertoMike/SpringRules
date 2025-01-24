package io.github.robertomike.springrules.constraints

import io.github.robertomike.jakidate.constraints.SimpleConstraint
import io.github.robertomike.springrules.validations.FileSize
import org.springframework.util.unit.DataSize
import org.springframework.web.multipart.MultipartFile

/**
 * FileSizeConstraint is a class that provides a custom validation constraint for file size.
 * It validates the size of a MultipartFile object against the minimum and maximum allowed sizes.
 *
 * @author Roberto Micheletti
 * @since 1.0.0
 */
class FileSizeConstraint : SimpleConstraint<FileSize, MultipartFile>() {
    /**
     * Checks if the size of the provided MultipartFile is within the allowed range.
     *
     * The allowed range is defined by the FileSize annotation on the method or field.
     *
     * @param value the MultipartFile object to be validated
     * @return true if the file size is within the allowed range, false otherwise
     * @see FileSize
     */
    override fun isValid(value: MultipartFile): Boolean {
        val size = DataSize.ofBytes(value.size)

        val max = DataSize.of(annotation.max, annotation.typeUnit)
        val min = DataSize.of(annotation.min, annotation.typeUnit)

        return size in min..max
    }
}