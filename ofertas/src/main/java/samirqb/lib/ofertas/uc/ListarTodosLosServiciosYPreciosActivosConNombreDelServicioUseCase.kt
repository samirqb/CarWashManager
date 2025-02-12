package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository

class ListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase(
    private val mRepository: ServiciosYPreciosRepository
) {
    operator fun invoke(precio_activo: Boolean): Flow<Map<ServicioYPrecioEntity, String>> {
        return mRepository.leerTodoPorPrecioActivoConNombreDelServicio(precio_activo)
    }
}