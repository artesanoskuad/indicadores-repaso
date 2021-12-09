package com.artesanoskuad.indicadoresrepaso.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artesanoskuad.indicadoresrepaso.domain.ObtenerIndicadoresUseCase

class IndicadoresViewModelFactory(
    private val obtenerIndicadoresUseCase: ObtenerIndicadoresUseCase
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndicadoresViewModel::class.java)) {
            return IndicadoresViewModel(obtenerIndicadoresUseCase) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }

}