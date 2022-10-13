package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions

@Entity(tableName = "account")
data class Account(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    val accountName: String,

    @ColumnInfo(name = "balance")
    var accountBalance: Double = 0.00,
)