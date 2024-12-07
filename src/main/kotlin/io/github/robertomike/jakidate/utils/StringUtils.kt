package io.github.robertomike.jakidate.utils

import io.github.robertomike.jakidate.exceptions.RulesException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

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