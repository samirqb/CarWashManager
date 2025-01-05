package samirqb.lib.personas.cu

import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.repositories.ClienteRepository

class AgregarClienteUseCase(private val mRepository: ClienteRepository) {
    suspend operator fun invoke(mTEntity: ClienteEntity){
        mRepository.insertar(mTEntity)
    }
}
