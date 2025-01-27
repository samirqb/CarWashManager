package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository

class ListarTodosLosServiciosYPreciosUseCase(
    private val mRepository: ServiciosYPreciosRepository
) {
    operator fun invoke(): Flow<List<ServicioYPrecioEntity>> {
        return mRepository.leerTodo()
    }
}