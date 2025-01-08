package samirqb.lib.personas.uc

import samirqb.lib.personas.entities.OperarioEntity
import samirqb.lib.personas.repositories.OperarioRepository

class AgregarOperarioUseCase(private val mRepository: OperarioRepository) {
    suspend operator fun invoke(mTEntity: OperarioEntity){
        mRepository.insertar(mTEntity)
    }
}