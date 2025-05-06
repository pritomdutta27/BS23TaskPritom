package com.pritomdutta.bs23androidtask.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


fun String.getDateTime(dateFormat: String, outFormat: String): String? {
    val input = SimpleDateFormat(dateFormat, Locale.getDefault())
    input.timeZone = TimeZone.getTimeZone("UTC")
    val output = SimpleDateFormat(outFormat, Locale.getDefault())
//    output.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
    try {
        val getAbbreviate = input.parse(this)    // parse input
        return getAbbreviate?.let { output.format(it) }    // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}