package com.example.myapplication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// класс со всеми item'ами
@Entity (tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "tel_num")
    var tel_num: String,

    )