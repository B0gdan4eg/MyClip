package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TransactionDao {

    @Insert
    fun insert(transactions: Transactions)

    @Update
    fun update(transactions: Transactions)

    @Delete
    fun clear(transactions: Transactions)
}