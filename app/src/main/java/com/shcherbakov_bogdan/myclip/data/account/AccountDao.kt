package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.*

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Query("SELECT * FROM account")
    suspend fun getListOfAccounts(): MutableList<Account>
}