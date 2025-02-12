package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository

class ListarTodosLosServiciosYPreciosConNomnreUseCase(
    private val mRepository: ServiciosYPreciosRepository
) {
    operator fun invoke(): Flow<Map<ServicioYPrecioEntity, String>> {
        return mRepository.leerTodoConNombreDelServicio()
    }
}