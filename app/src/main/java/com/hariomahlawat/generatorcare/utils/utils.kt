package com.hariomahlawat.generatorcare.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: Date): String {
    val format = SimpleDateFormat("dd MMM yyyy",
        Locale.getDefault())
    return format.format(date)
}