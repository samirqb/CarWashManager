package samirqb.lib.ofertas.uc

import samirqb.lib.ofertas.entities.ServicioEntity
import samirqb.lib.ofertas.repositories.ServiciosRepository

class AgregarServicioUseCase(
    private val mRepository: ServiciosRepository
) {
    suspend operator fun invoke(mTEntity: ServicioEntity) {
        mRepository.insertar(mTEntity)
    }
}