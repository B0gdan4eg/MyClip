package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactions: Transactions)

    @Query("SELECT * FROM transactions")
    suspend fun getListOfTransactions(): MutableList<Transactions>
}