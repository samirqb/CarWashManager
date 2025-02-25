package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity

data class DetalleOrdenServicioUiState(
    val fecha_y_hora: String = "",
    val nuevos_servicios_por_agregar_a_la_orden: MutableList<DetalleOrdenServicioEntity> = mutableStateListOf(),
    val nuevos_precios_de_servicios_por_agregar_a_la_orden: MutableList<Float> = mutableStateListOf(),
    val todos_los_servicios_agregados_a_la_orden: MutableList<DetalleOrdenServicioEntity> = mutableStateListOf(),
    val todos_los_precios_de_servicios_agregados_a_la_orden: MutableList<Float> = mutableStateListOf(),
    val suma_precios_servicios_agregados_a_orden: MutableFloatState = mutableFloatStateOf(0f),
    val suma_precios_servicios_por_agregar_a_orden: MutableFloatState = mutableFloatStateOf(0f),
)
