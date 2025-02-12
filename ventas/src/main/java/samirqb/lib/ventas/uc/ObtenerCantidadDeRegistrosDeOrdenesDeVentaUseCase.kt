package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    operator fun invoke(): Flow<Int>{
        return mRepository.obtenerCantidadDeRegistros()
    }
}