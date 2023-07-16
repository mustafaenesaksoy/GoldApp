package com.enesaksoy.goldapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesaksoy.goldapp.repo.GoldRepository
import com.enesaksoy.goldapp.roomdb.IslemListesiModel
import com.enesaksoy.goldapp.roomdb.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoldViewModel @Inject constructor(private val repo : GoldRepository) : ViewModel() {

    val getList = repo.getFirmaList()

    fun insertFirma(model : Model){
        viewModelScope.launch {
            repo.insertFirma(model)
        }
    }

    fun deleteFirma(model : Model){
        viewModelScope.launch {
            repo.deleteFirma(model)
        }
    }

    fun updateModel(islemList : List<IslemListesiModel>, id : Int){
        viewModelScope.launch {
            repo.updateModel(islemList, id)
        }
    }

    private val getModel = MutableLiveData<Model>()
    val getmodel : LiveData<Model>
    get() = getModel

    fun getModel(id : Int){
        viewModelScope.launch {
            getModel.value = repo.getId(id)
        }
    }

    fun updateTop(topIsc: Double, topSon: Double, id: Int){
        viewModelScope.launch {
            repo.updateTop(topIsc, topSon, id)
        }
    }
}