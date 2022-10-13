package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialty: List<Transactions>)

    @Query("SELECT * FROM transactions ORDER BY date ASC")
    fun getListOfTransactions(): LiveData<List<Transactions>>
}