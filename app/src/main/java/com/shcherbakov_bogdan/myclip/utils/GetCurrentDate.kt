package com.shcherbakov_bogdan.myclip.utils

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Date
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
fun Date.toString(locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat("dd/M/yyyy", locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): java.util.Date {
    return Calendar.getInstance().time
}