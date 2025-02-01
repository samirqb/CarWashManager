package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories

class ListarTodasLasOrdenesDePagoNominaUseCase(
    private val mRepository: OrdenPagoNominaRepositories
) {
    operator fun invoke(): Flow<List<OrdenPagoNominaEntity>> {
        return mRepository.leerTodo()
    }
}
