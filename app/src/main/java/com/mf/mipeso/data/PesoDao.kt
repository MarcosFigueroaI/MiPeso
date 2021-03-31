package com.mf.mipeso.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mf.mipeso.model.Peso

@Dao
interface PesoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPeso(peso: Peso)

    @Update
    suspend fun updatePeso(peso: Peso)

    @Delete
    suspend fun deletePeso(peso: Peso)

    @Query("delete from peso_table")
    suspend fun deleteAllPesos()

    @Query("select * from peso_table order by id desc")
    fun getPeso(): LiveData<List<Peso>>
}