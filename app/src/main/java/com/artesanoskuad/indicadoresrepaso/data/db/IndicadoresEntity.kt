package com.artesanoskuad.indicadoresrepaso.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class IndicadoresEntity(
    @PrimaryKey
    val codigo: String,
    val nombre: String,
    @SerializedName("unidad_medida")
    val unidadMedida: String,
    var fecha: String,
    val valor: String
)
