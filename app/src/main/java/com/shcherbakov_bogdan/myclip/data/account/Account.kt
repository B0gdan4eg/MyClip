package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shcherbakov_bogdan.myclip.data.transactions.Transactions

@Entity(tableName = "money_account")
data class Account(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "account_name")
    val accountName: String,

    @ColumnInfo(name = "account_balance")
    var accountBalance: Double = 0.00,

    @ColumnInfo(name = "account_transactions")
    val accountTransactions: MutableList<Transactions>
)