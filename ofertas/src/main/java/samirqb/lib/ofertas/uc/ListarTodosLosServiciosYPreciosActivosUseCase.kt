package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository

class ListarTodosLosServiciosYPreciosActivosUseCase(
    private val mServiciosYPreciosRepository: ServiciosYPreciosRepository
) {
    operator fun invoke(precio_activo: Boolean): Flow<List<ServicioYPrecioEntity>> {
        return mServiciosYPreciosRepository.leerTodoPorPrecioActivo(precio_activo)
    }
}