package com.artesanoskuad.indicadoresrepaso.data.model

import com.google.gson.annotations.SerializedName

data class IndicadoresRetrofit(
    val version: String,
    val autor: String,
    val fecha: String,
    val uf: IndicadorRetrofit,
    val ivp: IndicadorRetrofit,
    val dolar: IndicadorRetrofit,
    @SerializedName("dolar_intercambio")
    val dolarIntercambio: IndicadorRetrofit,
    val euro: IndicadorRetrofit,
    val ipc: IndicadorRetrofit,
    val utm: IndicadorRetrofit,
    val imacec: IndicadorRetrofit,
    val tpm: IndicadorRetrofit,
    @SerializedName("libra_cobre")
    val libraCobre: IndicadorRetrofit,
    @SerializedName("tasa_desempleo")
    val tasaDesempleo: IndicadorRetrofit,
    val bitcoin: IndicadorRetrofit
)