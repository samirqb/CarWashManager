package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase(
    private val mRepositories: OrdenPagoNominaRepository
) {
    operator fun invoke( operario_id: String, orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        return mRepositories.leerPorOperarioIdYOrdenVigente(operario_id,orden_vigente)
    }
}