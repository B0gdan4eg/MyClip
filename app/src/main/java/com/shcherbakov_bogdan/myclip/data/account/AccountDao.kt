package com.shcherbakov_bogdan.myclip.data.account

import androidx.room.*

@Dao
interface AccountDao {
    @Insert
    fun insert(account: Account)

    @Update
    fun update(account: Account)

    @Delete
    fun clear(account: Account)
}