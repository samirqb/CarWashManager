package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories

class ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase(
    private val mRepositories: OrdenPagoNominaRepositories
) {
    operator fun invoke( operario_id: String, orden_pagada: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        return mRepositories.leerPorOperarioIdYOrdenPagada(operario_id,orden_pagada)
    }
}