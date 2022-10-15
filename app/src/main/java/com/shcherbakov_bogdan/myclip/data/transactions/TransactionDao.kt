package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialty: List<Transactions>)

    @Query("SELECT * FROM transactions ORDER BY date ASC")
    fun getListOfTransactions(): MutableList<Transactions>
}