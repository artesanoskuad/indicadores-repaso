package com.artesanoskuad.indicadoresrepaso.data.model

import com.google.gson.annotations.SerializedName

data class IndicadorRetrofit(
    val codigo: String,
    val nombre: String,
    @SerializedName("unidad_medida")
    val unidadMedida: String,
    var fecha: String,
    val valor: String
)
