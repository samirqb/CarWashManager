package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase(
    private val mRepository: OrdenPagoNominaRepository
) {
    operator fun invoke(orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        return mRepository.leerTodoPorPagar(orden_vigente)
    }
}
