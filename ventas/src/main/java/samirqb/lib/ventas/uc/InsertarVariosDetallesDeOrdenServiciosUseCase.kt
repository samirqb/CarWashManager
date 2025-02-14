package samirqb.lib.ventas.uc

import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.repositories.DetalleOrdenServicioRepository

class InsertarVariosDetallesDeOrdenServiciosUseCase(
    private val mRepository: DetalleOrdenServicioRepository
) {
    suspend operator fun invoke(mEntities: List<DetalleOrdenServicioEntity>){
        mRepository.insertarVarios(mEntities)
    }
}