package samirqb.lib.vehiculos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.repositories.VehiculoRepository

class ListarTodosLosVehiculosUseCase(
    private val mRepo: VehiculoRepository
) {
    operator fun invoke() : Flow<List<VehiculoEntity>> {
        return mRepo.leerTodo()
    }
}