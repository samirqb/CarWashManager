package samirqb.lib.ofertas.uc

import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository

class AgregarServicioYPrecioUseCase(
    private val mRepository: ServiciosYPreciosRepository
) {
    suspend operator fun invoke(mTEntity: ServicioYPrecioEntity) {
        mRepository.insertar(mTEntity)
    }
}
