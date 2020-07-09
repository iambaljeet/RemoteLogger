package com.lib.remotelogger.utility

import java.text.SimpleDateFormat
import java.util.*

fun Date.toDateString(toFormat: String? = "yyyy-MM-dd"): String {
    val simpleDateFormat = SimpleDateFormat(toFormat)
    return simpleDateFormat.format(this)
}

fun Long.toDate(): Date {
    val date = Date(this)
    return date
}