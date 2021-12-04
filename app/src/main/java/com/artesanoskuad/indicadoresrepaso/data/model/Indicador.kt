package com.artesanoskuad.indicadoresrepaso.data.model

import com.google.gson.annotations.SerializedName

data class Indicador(
    val codigo: String,
    val nombre: String,
    @SerializedName("unidad_medida")
    val unidadMedida: String,
    val fecha: String,
    val valor: String
)
