package samirqb.lib.personas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.OperarioEntity
import samirqb.lib.personas.repositories.OperarioRepository

class ListarTodosLosOperariosActivosUseCase(private val mRespository: OperarioRepository) {
    operator fun invoke(operario_activo: Boolean): Flow<List<OperarioEntity>> {
        return mRespository.leerPorOperarioActivo(operario_activo)
    }
}