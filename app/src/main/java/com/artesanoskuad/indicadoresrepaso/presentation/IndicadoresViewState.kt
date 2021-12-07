package com.artesanoskuad.indicadoresrepaso.presentation

import com.artesanoskuad.indicadoresrepaso.data.model.Indicador


sealed class IndicadoresViewState{
    object NoSeObtieneRespuestaViewState : IndicadoresViewState()
    data class MostrarIndicadoresViewState(
        val version: String,
        val author: String,
        val fecha: String,
        val indicadores: List<Indicador>
    ) : IndicadoresViewState()
}