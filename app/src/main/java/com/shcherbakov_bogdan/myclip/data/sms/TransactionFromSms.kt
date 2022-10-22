package com.shcherbakov_bogdan.myclip.data.sms

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactionsFromSms")
data class TransactionFromSms(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "condition")
    val condition: Boolean,

    @ColumnInfo(name = "account")
    val account: Long,
)
