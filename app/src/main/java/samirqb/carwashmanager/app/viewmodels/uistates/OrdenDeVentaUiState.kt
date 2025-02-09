package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

data class OrdenDeVentaUiState(
    val numero_de_orden_de_venta: Int = 0,
    val fecha_y_hora: String = "",
    val todas_las_ordenes_de_venta: List<OrdenDeVentaEntity> = listOf(),
    val todas_los_servicios_agregados: List<DetalleOrdenServicioEntity> = listOf(),
    val todas_los_productos_agregados: List<DetalleOrdenProductoEntity> = listOf(),
)