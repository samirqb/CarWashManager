package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class BuscarOrdenDeVentaPorIdUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    operator fun invoke(id: Int): Flow<OrdenDeVentaEntity> {
        return mRepository.leerPorId(id)
    }
}