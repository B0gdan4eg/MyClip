package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "balance")
    var balance: Double = 0.00,

    @ColumnInfo(name = "icon")
    val icon: String = "@drawable/ic_baseline_description"
) {
    override fun toString(): String {
        return "$id $name $balance $icon"
    }
}
