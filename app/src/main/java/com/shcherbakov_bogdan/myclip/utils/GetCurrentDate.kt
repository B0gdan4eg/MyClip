package com.shcherbakov_bogdan.myclip.utils

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun splitStrDate(str: String, pos: Int): String {
    val strS = str.split(" ") as ArrayList
    when (pos) {
        0 -> return strS[0] //Day of week
        1 -> return strS[1] + ". " + strS[5]  //Month. Year
        2 -> return strS[2] //Date
    }
    return str
}

fun getDate(long: Long, dateFormat: SimpleDateFormat): String {
    val date = Date(long)
    return dateFormat.format(date)
}