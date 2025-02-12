package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

data class OrdenDeVentaUiState(
    val numero_de_nueva_orden_de_venta: Int = 0,
    val fecha_y_hora: String = "",
    val todas_las_ordenes_de_venta: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_pagadas: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_sin_pagar: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todos_los_servicios_agregados_a_la_orden: MutableList<DetalleOrdenServicioEntity> = mutableStateListOf(),
    val todos_los_productos_agregados_a_la_orden: MutableList<DetalleOrdenProductoEntity> = mutableStateListOf(),
)