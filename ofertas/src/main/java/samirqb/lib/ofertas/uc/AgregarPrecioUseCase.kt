package samirqb.lib.ofertas.uc

import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.repositories.PrecioRepository


class AgregarPrecioUseCase(
    private val mRepository: PrecioRepository
) {
    suspend operator fun invoke(mTEntity: PrecioEntity) {
        mRepository.insertar(mTEntity)
    }
}