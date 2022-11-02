package com.shcherbakov_bogdan.myclip.utils

import java.text.SimpleDateFormat
import java.util.*

class Const {
    companion object {
        const val BASE_URL = "https://www.nbrb.by/api/exrates/"
        const val BASE_RATE = 1.0
        val DATE_FORMAT = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        val DATE_FORMAT_WEEK = SimpleDateFormat("EEE", Locale.UK)
    }
}
