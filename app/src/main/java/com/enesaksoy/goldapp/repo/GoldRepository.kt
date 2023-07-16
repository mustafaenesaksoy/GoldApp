package com.enesaksoy.goldapp.repo

import androidx.lifecycle.LiveData
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import com.enesaksoy.goldapp.roomdb.Model

interface GoldRepository {

    suspend fun insertFirma(model : Model)

    suspend fun deleteFirma(model : Model)

    suspend fun getId(id : Int) : Model

    fun getFirmaList() : LiveData<List<Model>>

    suspend fun updateModel(islemList : List<IslemListesiModel>, id : Int)

    suspend fun updateTop(topIsc : Double, topSon : Double ,id : Int)
}