package com.artesanoskuad.indicadoresrepaso.data.mapper

import com.artesanoskuad.indicadoresrepaso.data.db.IndicadorInfoEntity
import com.artesanoskuad.indicadoresrepaso.data.db.IndicadoresEntity
import com.artesanoskuad.indicadoresrepaso.data.model.IndicadorRetrofit
import com.artesanoskuad.indicadoresrepaso.data.model.IndicadoresRetrofit
import com.artesanoskuad.indicadoresrepaso.domain.Indicador
import com.artesanoskuad.indicadoresrepaso.domain.Indicadores

object IndicadoresMapper {
    fun remoteIndicadoresToDomainIndicadores(indicadores: IndicadoresRetrofit): Indicadores {
        with(indicadores) {
            return Indicadores(
                version,
                autor,
                fecha.substring(0, 10),
                indicadorListRemoteToDomainList(
                    listOf(
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
                    )
                )
            )
        }
    }

    fun remoteIndicadoresToDataIndicadores(indicadores: IndicadoresRetrofit): List<IndicadoresEntity> {
        with(indicadores) {
            return indicadorListRemoteToLocalList(
                listOf(
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
                )

            )
        }
    }

    fun indicadorListRemoteToDomainList(list: List<IndicadorRetrofit>): List<Indicador> {
        return list.map {
            indicadorRemoteToDomain(it)
        }
    }

    fun indicadorListRemoteToLocalList(list: List<IndicadorRetrofit>): List<IndicadoresEntity> {
        return list.map {
            indicadorRemoteToLocal(it)
        }
    }

    private fun indicadorRemoteToLocal(indicadoreRetrofit: IndicadorRetrofit): IndicadoresEntity {
        with(indicadoreRetrofit) {
            return IndicadoresEntity(
                codigo, nombre, unidadMedida, fecha.substring(0, 10), valor
            )
        }
    }

    private fun indicadorRemoteToDomain(indicadorRetrofit: IndicadorRetrofit): Indicador {
        with(indicadorRetrofit) {
            return Indicador(
                codigo,
                nombre,
                unidadMedida,
                fecha.substring(0, 10),
                valor
            )
        }
    }

    fun localIndicadoresToDomainIndicadores(
        indicadorInfoEntity: IndicadorInfoEntity,
        indicadores: List<IndicadoresEntity>
    ): Indicadores {
        with(indicadorInfoEntity) {
            return Indicadores(
                version,
                autor,
                fecha,
                localIndicadorListToDomainIndicadorList(indicadores)
            )
        }
    }

    private fun localIndicadorListToDomainIndicadorList(indicadores: List<IndicadoresEntity>): List<Indicador> {
        return indicadores.map {
            localIndicadorToDomainIndicador(it)
        }
    }

    private fun localIndicadorToDomainIndicador(indicadoresEntity: IndicadoresEntity): Indicador {
        with(indicadoresEntity) {
            return Indicador(
                codigo, nombre, unidadMedida, fecha, valor
            )
        }
    }

    fun remoteIndicadorInfoToDataIndicador(safeBody: IndicadoresRetrofit): IndicadorInfoEntity {
        return IndicadorInfoEntity(
            0,
            safeBody.version,
            safeBody.autor,
            safeBody.fecha.substring(0, 10)
        )
    }
}