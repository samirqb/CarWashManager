package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.repositories.PrecioRepository

class ObtenerElPrecioMasRecienteUseCase(
    private val mRepository: PrecioRepository
) {
    operator fun invoke(): Flow<PrecioEntity?> {
        return mRepository.leerMasReciente()
    }
}