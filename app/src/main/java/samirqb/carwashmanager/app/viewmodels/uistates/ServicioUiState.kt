package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.ofertas.entities.ServicioEntity

data class ServicioUiState(
    val todos_los_servicios:List<ServicioEntity> = listOf(),
    val fecha_y_hora: String = "",
)