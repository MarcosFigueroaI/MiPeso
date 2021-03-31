package com.mf.mipeso.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mf.mipeso.data.PesoDao
import com.mf.mipeso.model.Peso

class PesoRepository(private val pesoDao: PesoDao) {

    val getPeso: LiveData<List<Peso>> = pesoDao.getPeso()

    suspend fun addPeso(peso: Peso) {
        pesoDao.addPeso(peso)
    }

    suspend fun updatePeso(peso: Peso) {
        pesoDao.updatePeso(peso)
    }

    suspend fun deletePeso(peso: Peso) {
        pesoDao.deletePeso(peso)
    }

    suspend fun deleteAllPesos() {
        pesoDao.deleteAllPesos()
    }
}