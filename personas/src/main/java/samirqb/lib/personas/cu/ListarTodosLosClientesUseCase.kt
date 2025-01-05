package samirqb.lib.personas.cu

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.repositories.ClienteRepository

class ListarTodosLosClientesUseCase(private val mRepository: ClienteRepository){
    operator fun invoke() : Flow<List<ClienteEntity>> {
        return mRepository.leerTodo()
    }
}