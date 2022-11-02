package com.shcherbakov_bogdan.myclip.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "currency")
data class CurrencyRates(

    @PrimaryKey
    @Json(name = "Cur_ID")
    var id: Int,

    @Json(name = "Date")
    var date: String,

    @Json(name = "Cur_Abbreviation")
    var abbreviation: String,

    @Json(name = "Cur_Scale")
    var scale: Int,

    @Json(name = "Cur_Name")
    var name: String,

    @Json(name = "Cur_OfficialRate")
    var rate: Double,


) {
    override fun toString(): String {
        return "$id $date $abbreviation $scale $name $rate"
    }
}