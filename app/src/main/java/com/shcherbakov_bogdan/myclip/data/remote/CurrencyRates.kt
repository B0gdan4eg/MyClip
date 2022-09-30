package com.shcherbakov_bogdan.myclip.data.remote

data class CurrencyRates(
    val Cur_Abbreviation: String,
    val Cur_ID: Int,
    val Cur_Name: String,
    val Cur_OfficialRate: Double,
    val Cur_Scale: Int,
    val Date: String
    )