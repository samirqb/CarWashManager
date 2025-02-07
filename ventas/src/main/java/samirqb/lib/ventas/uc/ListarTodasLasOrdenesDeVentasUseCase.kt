package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class ListarTodasLasOrdenesDeVentasUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    operator fun invoke(): Flow<List<OrdenDeVentaEntity>> {
        return mRepository.leerTodo()
    }
}