package com.darklabs.darknews.androidApp.util

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_END_USER = "dd, MMM, yyyy, hh:mm a"

fun String?.toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone: TimeZone = TimeZone
    .getTimeZone("UTC")): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}