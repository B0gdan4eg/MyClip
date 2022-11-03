package com.shcherbakov_bogdan.myclip.utils

import java.text.SimpleDateFormat
import java.util.*


fun splitStrDate(str: String, pos: Int): String {
    val strS = str.split("/") as ArrayList
    when (pos) {
        0 -> return strS[0] //Day
        1 -> return strS[1] + "." + str[2]  //Month //Year
    }
    return str
}

fun getDate(long: Long, dateFormat: SimpleDateFormat): String {
    val date = Date(long)
    return dateFormat.format(date)
}