package com.enesaksoy.goldapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity("firma")
class Model(
    val name : String,
    val islemList : List<IslemListesiModel>,
    val topIscilik : Double,
    val topSonuc : Double,
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
    )

class TypeConverter{
    @androidx.room.TypeConverter
    fun listToJson(value: List<IslemListesiModel>?) = Gson().toJson(value)

    @androidx.room.TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<IslemListesiModel>::class.java).toList()
}