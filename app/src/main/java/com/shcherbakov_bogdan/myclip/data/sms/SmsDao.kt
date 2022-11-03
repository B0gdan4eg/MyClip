package com.shcherbakov_bogdan.myclip.data.sms

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactionFromSms: TransactionFromSms)

    @Query("SELECT * FROM transactionsFromSms")
    suspend fun getListOfTransactionFromSms(): MutableList<TransactionFromSms>
}