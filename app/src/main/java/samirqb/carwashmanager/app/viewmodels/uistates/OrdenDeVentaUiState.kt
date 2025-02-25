package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

data class OrdenDeVentaUiState(
    val fecha_y_hora: String = "",
    val numero_de_orden_de_venta_seleccionada: Int = 0,
    val numero_calculado_para_nueva_orden_de_venta: Int = 0,
    val todas_las_ordenes_de_venta: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_vigentes: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val todas_las_ordenes_de_venta_no_vigente: MutableList<OrdenDeVentaEntity> = mutableStateListOf(),
    val resultado_busqueda_orden_de_venta: MutableState<OrdenDeVentaEntity?> = mutableStateOf(null),
)