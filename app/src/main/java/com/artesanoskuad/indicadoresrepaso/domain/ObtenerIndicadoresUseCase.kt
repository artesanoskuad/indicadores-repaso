package com.artesanoskuad.indicadoresrepaso.domain

class ObtenerIndicadoresUseCase(
    private val indicadoresRepository: IndicadoresRepository
) {
    suspend fun execute() = indicadoresRepository.obtenerIndicadores()
}
