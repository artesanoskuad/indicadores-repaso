package com.artesanoskuad.indicadoresrepaso.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val URL_BASE = "https://mindicador.cl/"

    fun crearApiIndicadores() : IndicadoresApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(IndicadoresApi::class.java)
    }
}