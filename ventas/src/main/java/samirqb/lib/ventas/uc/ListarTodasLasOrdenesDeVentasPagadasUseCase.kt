package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class ListarTodasLasOrdenesDeVentasPagadasUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    operator fun invoke(orden_pagada: Boolean): Flow<List<OrdenDeVentaEntity>> {
        return mRepository.leerPorOrdenPagada(orden_pagada)
    }
}