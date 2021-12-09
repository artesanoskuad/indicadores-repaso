package com.artesanoskuad.indicadoresrepaso.domain

interface IndicadoresRepository {
    suspend fun obtenerIndicadores(): Indicadores
}
