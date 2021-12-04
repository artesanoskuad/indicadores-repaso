package com.artesanoskuad.indicadoresrepaso.presentation

import com.artesanoskuad.indicadoresrepaso.data.model.Indicador


sealed class IndicadoresViewState{
    object ServerErrorIndicadoresViewState : IndicadoresViewState()
    data class ShowAllIndicadoresViewState(
        val version: String,
        val author: String,
        val fecha: String,
        val indicadores: List<Indicador>
    ) : IndicadoresViewState()
    // definir estado para no internet o evaluar mostrar datos offline
    // definir estado para cuando el "feature flag" este apagado
}