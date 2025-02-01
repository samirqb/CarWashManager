package samirqb.lib.personas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.repositories.ClienteRepository

class BuscarClientePorIdUseCase(
    private val mRepository: ClienteRepository
) {
    operator fun invoke(id: String): Flow<ClienteEntity> {
        return mRepository.leerPorId(id)
    }
}