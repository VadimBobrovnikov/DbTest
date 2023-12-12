package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//интерфейс, в котором прописываются методы, того, что может делать бд

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)
    @Delete
    fun deleteItem(item: Item)

    @Query("DELETE FROM items")
    fun deleteAllItems()
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

}