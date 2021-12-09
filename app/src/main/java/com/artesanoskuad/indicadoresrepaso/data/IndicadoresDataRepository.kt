package com.artesanoskuad.indicadoresrepaso.data

import com.artesanoskuad.indicadoresrepaso.data.db.IndicadoresDao
import com.artesanoskuad.indicadoresrepaso.data.mapper.IndicadoresMapper
import com.artesanoskuad.indicadoresrepaso.data.model.IndicadoresRetrofit
import com.artesanoskuad.indicadoresrepaso.data.remote.IndicadoresApi
import com.artesanoskuad.indicadoresrepaso.domain.Indicadores
import com.artesanoskuad.indicadoresrepaso.domain.IndicadoresRepository
import java.net.UnknownHostException

class IndicadoresDataRepository(
    private val api: IndicadoresApi,
    private val db: IndicadoresDao
) : IndicadoresRepository {
    override suspend fun obtenerIndicadores(): Indicadores {
        try {
            val indicadores = api.obtenerIndicadores()
            if (indicadores.isSuccessful) {
                val body = indicadores.body() ?: throw RuntimeException("Body no puede ser null")
                guardarDatosEnLocal(body)
                return IndicadoresMapper.remoteIndicadoresToDomainIndicadores(body)
            } else {
                return getIndicadoresFromLocal()
            }
        } catch (e: UnknownHostException) {
            return getIndicadoresFromLocal()
        } catch (e: Exception) {
            return getIndicadoresFromLocal()
        }
    }

    private fun getIndicadoresFromLocal(): Indicadores {
        try {
            val indicadorInfoLocal = db.getIndicadorInfo()
            if (indicadorInfoLocal == null) {
                return Indicadores.crearIndicadorVacio()
            } else {
                val listLocalIndicador = db.getIndicadoresList() ?: emptyList()
                return IndicadoresMapper.localIndicadoresToDomainIndicadores(
                    indicadorInfoLocal,
                    listLocalIndicador
                )
            }
        } catch (e: Exception) {
            return Indicadores.crearIndicadorVacio()
        }
    }

    private fun guardarDatosEnLocal(body: IndicadoresRetrofit?) {
        body?.let { safeBody ->
            val indicadoresEntity = IndicadoresMapper.remoteIndicadoresToDataIndicadores(safeBody)
            val indicadorInfo = IndicadoresMapper.remoteIndicadorInfoToDataIndicador(safeBody)
            try {
                db.deleteIndicadoresEntity()
                db.deleteIndicadoresInfoEntity()
                db.insert(indicadorInfo)
                db.insertAll(indicadoresEntity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}