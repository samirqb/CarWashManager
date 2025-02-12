package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class ObtenerOrdenDeVentaMasRecienteUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    operator fun invoke(): Flow<OrdenDeVentaEntity?> {
        return mRepository.leerMasReciente()
    }
}