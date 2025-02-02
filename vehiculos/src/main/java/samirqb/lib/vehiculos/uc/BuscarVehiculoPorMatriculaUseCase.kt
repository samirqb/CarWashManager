package samirqb.lib.vehiculos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.repositories.VehiculoRepository

class BuscarVehiculoPorMatriculaUseCase(
    private val mRepository: VehiculoRepository
) {
    operator fun invoke(id:String): Flow<VehiculoEntity> {
        return mRepository.leerPorId(id)
    }
}