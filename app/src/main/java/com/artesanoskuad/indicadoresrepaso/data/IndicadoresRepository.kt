package com.artesanoskuad.indicadoresrepaso.data

import com.artesanoskuad.indicadoresrepaso.data.model.Indicadores
import com.artesanoskuad.indicadoresrepaso.data.remote.IndicadoresApi
import java.lang.RuntimeException

class IndicadoresRepository(
    private val api: IndicadoresApi
) {
    suspend fun obtenerIndicadores(): Indicadores? {
        val indicadores = api.obtenerIndicadores()
        if(indicadores.isSuccessful){
            return indicadores.body()
        } else {
            throw RuntimeException("No se obtuvieron indicadores")
        }
    }
}