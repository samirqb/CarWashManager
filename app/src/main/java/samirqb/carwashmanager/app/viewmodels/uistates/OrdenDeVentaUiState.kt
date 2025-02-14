package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

data class OrdenDeVentaUiState(
    val numero_de_nueva_orden_de_venta: Int = 0,
    val fecha_y_hora: String = "",
    val lista_operarios_relacionados_con_orden_venta: MutableList<String> = mutableStateListOf(),
    val todas_las_ordenes_de_venta: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_pagadas: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_sin_pagar: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todos_los_servicios_agregados_a_la_orden: MutableList<DetalleOrdenServicioEntity> = mutableStateListOf(),
    val todos_los_precios_de_servicios_agregados_a_la_orden: MutableList<Float> = mutableStateListOf(0f),
    val suma_precios_servicios_agregados_a_orden: MutableFloatState = mutableFloatStateOf(0f),
    val suma_precios_productos_agregados_a_orden: MutableFloatState = mutableFloatStateOf(0f),
    val todos_los_productos_agregados_a_la_orden: MutableList<DetalleOrdenProductoEntity> = mutableStateListOf(),
)