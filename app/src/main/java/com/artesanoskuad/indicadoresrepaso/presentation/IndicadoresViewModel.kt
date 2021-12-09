package com.artesanoskuad.indicadoresrepaso.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artesanoskuad.indicadoresrepaso.data.model.IndicadorRetrofit
import com.artesanoskuad.indicadoresrepaso.domain.Indicadores
import com.artesanoskuad.indicadoresrepaso.domain.ObtenerIndicadoresUseCase
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.MostrarIndicadoresViewState
import com.artesanoskuad.indicadoresrepaso.presentation.IndicadoresViewState.NoSeObtieneRespuestaViewState
import kotlinx.coroutines.launch

class IndicadoresViewModel(
    private val obtenerIndicadoresUseCase: ObtenerIndicadoresUseCase
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<IndicadoresViewState>()

    fun state(): LiveData<IndicadoresViewState> = mutableLiveData

    fun obtenerIndicadores() {
        viewModelScope.launch {
            try {
                val indicadores = obtenerIndicadoresUseCase.execute()
                handleState(indicadores)
            } catch (e: Exception) {
                mutableLiveData.postValue(NoSeObtieneRespuestaViewState)
            }
        }
    }

    private fun handleState(indicadores: Indicadores) {
        mutableLiveData.postValue(
            MostrarIndicadoresViewState(indicadores)
        )
    }
}
