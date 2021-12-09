package com.artesanoskuad.indicadoresrepaso.domain

data class Indicador(
    val codigo: String,
    val nombre: String,
    val unidadMedida: String,
    var fecha: String,
    val valor: String
)
