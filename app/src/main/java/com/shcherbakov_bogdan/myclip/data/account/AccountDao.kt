package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Query("SELECT * FROM account")
    suspend fun getListOfAccounts(): MutableList<Account>
}