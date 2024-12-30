package io.github.robertomike.jakidate.utils

import io.github.robertomike.jakidate.exceptions.RulesException
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

/**
 * A list of strings representing accepted values for boolean-like strings.
 *
 * @see io.github.robertomike.jakidate.constraints.AcceptedConstraint
 */
val acceptedStringValues = listOf("yes", "on", "true", "y", "1")

/**
 * A list of strings representing declined values for boolean-like strings.
 *
 * @see io.github.robertomike.jakidate.constraints.DeclinedConstraint
 */
val declinedStringValues = listOf("no", "off", "false", "n", "0")

/**
 * Converts a string to a Date object using the specified format.
 *
 * @param format the format of the date string
 * @return the Date object representation of the string
 * @throws RulesException if the date string is invalid
 */
fun String.toDate(format: String): Date {
    return try {
        SimpleDateFormat(format).parse(this)
    } catch (e: Exception) {
        throw RulesException("Invalid date format", e)
    }
}

/**
 * Converts a string to a LocalDateTime object.
 *
 * @return the LocalDateTime object representation of the string
 * @throws RulesException if the date string is invalid
 */
fun String.toLocalDateTime(): LocalDateTime {
    return try {
        LocalDateTime.parse(this)
    } catch (e: Exception) {
        throw RulesException("Invalid date format", e)
    }
}

/**
 * Generates the SHA-1 hash of the string.
 *
 * @return the SHA-1 hash of the string as a hexadecimal string
 */
fun String.sha1(): String {
    val md = MessageDigest.getInstance("SHA-1")
    val messageDigest = md.digest(this.toByteArray())
    return buildString {
        messageDigest.forEach {
            this.append(String.format("%02x", it))
        }
    }
}

/**
 * Converts a string to a map of key-value pairs.
 *
 * The string is expected to be in the format "key1|value1\key2|value2\...", where "\"
 * is the separator and "|" is the key-value separator.
 *
 * @return the map of key-value pairs
 */
fun String.specialToMap(): Map<String, Int> {
    return this.split("\\")
        .associate {
            val (key, value) = it.split("|")
            key to value.toInt()
        }
}