package io.github.robertomike.jakidate.utils

import io.github.robertomike.jakidate.exceptions.RulesException
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


val acceptedStringValues = listOf("yes", "on", "true", "y", "1")
val declinedStringValues = listOf("no", "off", "false", "n", "0")

fun String.toDate(format: String): Date {
    return try {
        SimpleDateFormat(format).parse(this)
    } catch (e: Exception) {
        throw RulesException("Invalid date format", e)
    }
}


fun String.toLocalDateTime(): LocalDateTime {
    return try {
        LocalDateTime.parse(this)
    } catch (e: Exception) {
        throw RulesException("Invalid date format", e)
    }
}

fun String.sha1(): String {
    val md = MessageDigest.getInstance("SHA-1")
    val messageDigest = md.digest(this.toByteArray())
    return buildString {
        messageDigest.forEach {
            this.append(String.format("%02x", it))
        }
    }
}