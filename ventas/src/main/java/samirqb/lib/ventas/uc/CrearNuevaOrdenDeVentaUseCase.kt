package samirqb.lib.ventas.uc

import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository

class CrearNuevaOrdenDeVentaUseCase(
    private val mRepository: OrdenDeVentaRepository
) {
    suspend operator fun invoke(mEntity: OrdenDeVentaEntity){
        mRepository.insertar(mEntity)
    }
}