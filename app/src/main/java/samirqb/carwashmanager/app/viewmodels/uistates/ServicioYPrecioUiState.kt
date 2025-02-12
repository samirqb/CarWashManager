package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity

data class ServicioYPrecioUiState(
    val todos_los_servicios_y_precios: MutableList<ServicioYPrecioEntity> = mutableStateListOf(),
    val todos_los_servicios_y_precios_con_nombre: MutableMap<ServicioYPrecioEntity, String> = mutableStateMapOf(),
    val listar_todos_los_servicios_y_precios_activos: MutableList<ServicioYPrecioEntity> = mutableStateListOf(),
    val todos_los_servicios_y_precios_inactivos: MutableList<ServicioYPrecioEntity> = mutableStateListOf(),
    val todos_los_servicios_y_precios_activos_y_nommbre_del_servicio: Map<ServicioYPrecioEntity, String> = mapOf(),
    val todos_los_servicios_y_precios_inactivos_y_nommbre_del_servicio: Map<ServicioYPrecioEntity, String> = mapOf(),
    val fecha_y_hora: String = "",
)