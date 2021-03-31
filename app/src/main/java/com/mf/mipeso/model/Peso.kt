package com.mf.mipeso.model

import android.os.Parcelable
import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "peso_table")
data class Peso(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dia: String,
    val fecha: String,
    val peso: Float
): Parcelable