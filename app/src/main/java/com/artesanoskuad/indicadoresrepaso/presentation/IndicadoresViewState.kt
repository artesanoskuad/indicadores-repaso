package com.artesanoskuad.indicadoresrepaso.presentation

import com.artesanoskuad.indicadoresrepaso.domain.Indicadores


sealed class IndicadoresViewState{
    object NoSeObtieneRespuestaViewState : IndicadoresViewState()
    data class MostrarIndicadoresViewState(
        val indicadores: Indicadores
    ) : IndicadoresViewState()
}
