package com.artesanoskuad.indicadoresrepaso.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class IndicadoresViewModel : ViewModel() {

    private val mutableLiveData = MutableLiveData<IndicadoresViewState>()

    fun state() : LiveData<IndicadoresViewState> = mutableLiveData

    fun obtenerIndicadores(){
        viewModelScope.launch{

        }
    }
}