package samirqb.lib.vehiculos.uc

import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.repositories.VehiculoRepository

class AgregarVehiculoUseCase(
    private val mRepo: VehiculoRepository,
) {
    suspend operator fun invoke(mTEntity: VehiculoEntity){
        mRepo.insertar(mTEntity)
    }
}