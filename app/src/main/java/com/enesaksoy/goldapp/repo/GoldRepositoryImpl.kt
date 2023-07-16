package com.enesaksoy.goldapp.repo

import androidx.lifecycle.LiveData
import com.enesaksoy.goldapp.roomdb.GoldDao
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import com.enesaksoy.goldapp.roomdb.Model
import javax.inject.Inject

class GoldRepositoryImpl @Inject constructor(private val dao: GoldDao) : GoldRepository {
    override suspend fun insertFirma(model: Model) {
        dao.insertFirma(model)
    }

    override suspend fun deleteFirma(model: Model) {
        dao.deleteFirma(model)
    }

    override suspend fun getId(id: Int): Model {
        return dao.getId(id)
    }

    override fun getFirmaList(): LiveData<List<Model>> {
        return dao.getFirmaList()
    }

    override suspend fun updateModel(islemList: List<IslemListesiModel>, id: Int) {
        dao.updateModel(islemList, id)
    }

    override suspend fun updateTop(topIsc: Double, topSon: Double, id: Int) {
        dao.updateTop(topIsc, topSon, id)
    }
}