package com.artesanoskuad.indicadoresrepaso.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IndicadorInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val version: String,
    val autor: String,
    val fecha: String
)
