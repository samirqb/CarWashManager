package samirqb.lib.personas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.OperarioEntity
import samirqb.lib.personas.repositories.OperarioRepository

class ListarTodosLosOperariosUseCase(private val mRespository: OperarioRepository) {
    operator fun invoke(): Flow<List<OperarioEntity>> {
        return mRespository.leerTodo()
    }
}