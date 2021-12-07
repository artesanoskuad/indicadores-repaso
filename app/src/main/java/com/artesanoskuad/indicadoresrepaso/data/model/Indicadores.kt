package com.artesanoskuad.indicadoresrepaso.data.model

import com.google.gson.annotations.SerializedName

data class Indicadores(
    val version: String,
    val autor: String,
    val fecha: String,
    val uf: Indicador,
    val ivp: Indicador,
    val dolar: Indicador,
    @SerializedName("dolar_intercambio")
    val dolarIntercambio: Indicador,
    val euro: Indicador,
    val ipc: Indicador,
    val utm: Indicador,
    val imacec: Indicador,
    val tpm: Indicador,
    @SerializedName("libra_cobre")
    val libraCobre: Indicador,
    @SerializedName("tasa_desempleo")
    val tasaDesempleo: Indicador,
    val bitcoin: Indicador
)