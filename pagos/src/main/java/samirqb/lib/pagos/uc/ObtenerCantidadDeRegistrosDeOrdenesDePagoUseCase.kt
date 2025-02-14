package samirqb.lib.pagos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepository

class ObtenerCantidadDeRegistrosDeOrdenesDePagoUseCase(
    private val mRepository: OrdenPagoNominaRepository
) {
    operator fun invoke(): Flow<Int>{
        return mRepository.obtenerCantidadDeRegistros()
    }

}