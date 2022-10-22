package com.shcherbakov_bogdan.myclip.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currencyRates: List<CurrencyRates>)

    @Query("SELECT * FROM currency")
    suspend fun getListOfCurrency(): MutableList<CurrencyRates>

    @Query("DELETE FROM currency")
    suspend fun deleteAllCurrencies()
}