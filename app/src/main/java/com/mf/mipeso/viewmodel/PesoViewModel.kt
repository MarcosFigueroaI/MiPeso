package com.mf.mipeso.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mf.mipeso.data.PesoDatabase
import com.mf.mipeso.model.Peso
import com.mf.mipeso.repository.PesoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PesoViewModel(application: Application): AndroidViewModel(application) {

    val getPeso: LiveData<List<Peso>>
    private val repository: PesoRepository

    init {
        val pesoDao = PesoDatabase.getDatabase(application).pesoDao()
        repository = PesoRepository(pesoDao)
        getPeso = repository.getPeso
    }

    fun addPeso(peso: Peso) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPeso(peso)
        }
    }

    fun updatePeso(peso: Peso) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePeso(peso)
        }
    }

    fun deletePeso(peso: Peso) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePeso(peso)
        }
    }

    fun deleteAllPesos() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPesos()
        }
    }
}