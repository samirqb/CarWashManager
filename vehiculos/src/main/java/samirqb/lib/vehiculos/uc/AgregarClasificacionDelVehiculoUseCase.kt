package samirqb.lib.vehiculos.uc

import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.repositories.ClasificacionDelVehiculoRepository

class AgregarClasificacionDelVehiculoUseCase(
    private val mRepo: ClasificacionDelVehiculoRepository
) {
    suspend operator fun invoke(mTEntity: ClasificacionDelVehiculoEntity){
        mRepo.insertar(mTEntity)
    }
}