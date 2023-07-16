package com.enesaksoy.goldapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GoldDao {

    @Insert
    suspend fun insertFirma(model : Model)

    @Delete
    suspend fun deleteFirma(model : Model)

    @Query("SELECT * FROM firma WHERE id = :id")
    suspend fun getId(id : Int) : Model

    @Query("SELECT * FROM firma")
    fun getFirmaList() : LiveData<List<Model>>

    @Query("UPDATE firma SET islemList = :islemList WHERE id = :id")
    suspend fun updateModel(islemList : List<IslemListesiModel>, id : Int)

    @Query("UPDATE firma SET topIscilik = :topIsc , topSonuc = :topSon WHERE id = :id")
    suspend fun updateTop(topIsc : Double, topSon : Double ,id : Int)


}