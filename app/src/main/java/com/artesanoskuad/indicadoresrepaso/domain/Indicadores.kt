package com.artesanoskuad.indicadoresrepaso.domain

data class Indicadores(
    val version: String,
    val autor: String,
    val fecha: String,
    val indicadores: List<Indicador>
) {
    companion object {
        fun crearIndicadorVacio() = Indicadores(
            version = "",
            autor = "",
            fecha = "",
            emptyList()
        )
    }
}
