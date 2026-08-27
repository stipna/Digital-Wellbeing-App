package com.stephan.screenlock.ui.util

fun formatMinutesShort(minutes: Float): String {
    val total = minutes.toInt()
    val h = total / 60
    val m = total % 60
    return if (h > 0) "${h}h ${m}m" else "${m}m"
}

fun formatMinutesLong(minutes: Float): String {
    val total = minutes.toInt()
    val h = total / 60
    val m = total % 60
    return when {
        h > 0 && m > 0 -> "$h Stunden $m Minuten"
        h > 0 -> "$h Stunden"
        else -> "$m Minuten"
    }
}
