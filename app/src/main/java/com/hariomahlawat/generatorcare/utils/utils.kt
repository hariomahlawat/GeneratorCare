package com.hariomahlawat.generatorcare.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd MMM yyyy",
        Locale.getDefault())
    return format.format(date)
}