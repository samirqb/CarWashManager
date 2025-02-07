package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.ventas.entities.OrdenDeVentaEntity

data class OrdenDeVentaUiState(
    val fecha_y_hora: String = "",
    val todas_las_ordenes_de_venta: List<OrdenDeVentaEntity> = listOf(),
)