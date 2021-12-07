package com.artesanoskuad.indicadoresrepaso.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artesanoskuad.indicadoresrepaso.data.IndicadoresRepository

class IndicadoresViewModelFactory(
    private val indicadoresRepository: IndicadoresRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndicadoresViewModel::class.java)) {
            return IndicadoresViewModel(indicadoresRepository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }

}