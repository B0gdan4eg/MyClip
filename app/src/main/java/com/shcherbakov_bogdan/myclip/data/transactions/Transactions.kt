package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transactions(

    @ColumnInfo(name = "dayOfWeek")
    val dayOfWeek: String,

    @ColumnInfo(name = "amount")
    val amount: Double,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "condition")
    val condition: Boolean,

    @ColumnInfo(name = "category")
    val category: Long,

    @ColumnInfo(name = "account")
    val account: Long,

    @ColumnInfo(name = "description")
    val description: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}


fun getAmountOfTransaction(transactions: Transactions): String {
    return transactions.amount.toString()
}

fun getConditionOfTransaction(transactions: Transactions): Boolean {
    if (transactions.condition) {
        return true
    }
    return false
}