package samirqb.lib.ventas.uc

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.repositories.DetalleOrdenServicioRepository

class ListarLosServiciosAgregadoAUnaOrdenVentaUseCase(
    private val mRepository: DetalleOrdenServicioRepository
) {
    operator fun invoke(id_orden_venta: Int): Flow<List<DetalleOrdenServicioEntity>>{
        return mRepository.leerPorIdDeOrden(id_orden_venta)
    }
}