package com.artesanoskuad.indicadoresrepaso.data.remote

import com.artesanoskuad.indicadoresrepaso.data.model.IndicadoresRetrofit
import retrofit2.Response
import retrofit2.http.GET

interface IndicadoresApi {
    @GET("api")
    suspend fun obtenerIndicadores() : Response<IndicadoresRetrofit>
}