package com.shcherbakov_bogdan.myclip.data.transactions

import androidx.room.*

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactions: Transactions)

    @Update
    fun update(transactions: Transactions)

    @Delete
    fun clear(transactions: Transactions)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): List<Transactions>
}