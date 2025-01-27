package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.ofertas.entities.ServicioYPrecioEntity

data class ServicioYPrecioUiState(
    val todos_los_servicios_y_precios:List<ServicioYPrecioEntity> = listOf(),
    val fecha_y_hora: String = "",
)