package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shcherbakov_bogdan.myclip.data.category.Category
import com.shcherbakov_bogdan.myclip.utils.getCurrentDateTime
import java.util.*

@Entity(tableName = "transactions")
data class Transactions(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "transaction_amount")
    val amount: Double,

    @ColumnInfo(name = "transaction_date")
    val date: Date = getCurrentDateTime(),

    @ColumnInfo(name = "transaction_condition")
    val condition: Boolean,

    @ColumnInfo(name = "transaction_category")
    val category: Category,

    @ColumnInfo(name = "transaction_description")
    val description: String,
)
