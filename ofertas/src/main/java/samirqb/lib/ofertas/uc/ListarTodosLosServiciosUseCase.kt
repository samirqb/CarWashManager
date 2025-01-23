package samirqb.lib.ofertas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioEntity
import samirqb.lib.ofertas.repositories.ServiciosRepository

class ListarTodosLosServiciosUseCase(
    private val mRepository: ServiciosRepository
) {
    operator fun invoke(): Flow<List<ServicioEntity>> {
        return mRepository.leerTodo()
    }
}