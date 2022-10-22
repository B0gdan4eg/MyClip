package com.shcherbakov_bogdan.myclip.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "currency")
data class CurrencyRates(

    @PrimaryKey
    @Json(name = "Cur_ID")
    val id: Int,

    @Json(name = "Date")
    val date: String,

    @Json(name = "Cur_Abbreviation")
    val abbreviation: String,

    @Json(name = "Cur_Scale")
    val scale: Int,

    @Json(name = "Cur_Name")
    val name: String,

    @Json(name = "Cur_OfficialRate")
    val rate: Double,


) {
    override fun toString(): String {
        return "$id $date $abbreviation $scale $name $rate"
    }
}