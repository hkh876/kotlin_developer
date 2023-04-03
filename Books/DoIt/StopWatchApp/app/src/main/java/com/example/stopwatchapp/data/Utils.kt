package com.example.stopwatchapp.data

import java.util.concurrent.TimeUnit

fun msToTimeFormat(milliseconds: Long): String {
    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(milliseconds),
        TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60
    )
}