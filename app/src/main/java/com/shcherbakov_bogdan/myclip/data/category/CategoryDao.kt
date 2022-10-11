package com.shcherbakov_bogdan.myclip.data.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun clear(category: Category)
}