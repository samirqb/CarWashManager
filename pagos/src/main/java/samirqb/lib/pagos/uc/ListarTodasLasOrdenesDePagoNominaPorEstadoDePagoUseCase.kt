package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories

class ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase(
    private val mRepository: OrdenPagoNominaRepositories
) {
    operator fun invoke(orden_pagada: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        return mRepository.leerTodoPorPagar(orden_pagada)
    }
}
