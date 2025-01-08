package samirqb.lib.vehiculos.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.repositories.ClasificacionDelVehiculoRepository

class ListarTodasLasClasificacionesDelVehiculoUseCase(
    private val mRepo: ClasificacionDelVehiculoRepository
) {
    operator fun invoke() : Flow<List<ClasificacionDelVehiculoEntity>> {
        return mRepo.leerTodo()
    }
}