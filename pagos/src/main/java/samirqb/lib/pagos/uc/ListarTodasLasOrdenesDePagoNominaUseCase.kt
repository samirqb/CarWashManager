package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class ListarTodasLasOrdenesDePagoNominaUseCase(
    private val mRepository: OrdenPagoNominaRepository
) {
    operator fun invoke(): Flow<List<OrdenPagoNominaEntity>> {
        return mRepository.leerTodo()
    }
}
