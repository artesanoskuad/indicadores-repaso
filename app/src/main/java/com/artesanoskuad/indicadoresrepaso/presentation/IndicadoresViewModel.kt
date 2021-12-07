package com.artesanoskuad.indicadoresrepaso.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artesanoskuad.indicadoresrepaso.data.IndicadoresRepository
import com.artesanoskuad.indicadoresrepaso.data.model.Indicador
import com.artesanoskuad.indicadoresrepaso.data.model.Indicadores
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.MostrarIndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.NoSeObtieneRespuestaViewState
import kotlinx.coroutines.launch

class IndicadoresViewModel(
    private val indicadoresRepository: IndicadoresRepository
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<IndicadoresViewState>()

    fun state(): LiveData<IndicadoresViewState> = mutableLiveData

    fun obtenerIndicadores() {
        viewModelScope.launch {
            val indicadores = indicadoresRepository.obtenerIndicadores()
            handleState(indicadores)
        }
    }

    private fun handleState(indicadores: Indicadores?) {
        if (indicadores != null) {
            mutableLiveData.postValue(
                MostrarIndicadoresViewState(
                    indicadores.version,
                    indicadores.autor,
                    formatearFecha(indicadores.fecha),
                    getListIndicadores(indicadores)
                )
            )
        } else {
            mutableLiveData.postValue(NoSeObtieneRespuestaViewState)
        }
    }

    private fun getListIndicadores(indicadores: Indicadores): List<Indicador> {
        with(indicadores) {
            return listOf(
                uf,
                ivp,
                dolar,
                dolarIntercambio,
                euro,
                ipc,
                utm,
                imacec,
                tpm,
                libraCobre,
                tasaDesempleo,
                bitcoin
            ).map { indicador ->
                getIndicadorFechaFormateada(indicador)
            }
        }
    }

    private fun formatearFecha(fecha: String) = fecha.substring(0,10)

    private fun getIndicadorFechaFormateada(indicador: Indicador): Indicador {
        with(indicador) {
            return Indicador(
                codigo,
                nombre,
                unidadMedida,
                formatearFecha(fecha),
                valor
            )
        }
    }
}