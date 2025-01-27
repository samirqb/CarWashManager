package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.ofertas.entities.PrecioEntity

data class PrecioUiState(
    val todos_los_precios:List<PrecioEntity> = listOf(),
    val fecha_y_hora: String = "",
)