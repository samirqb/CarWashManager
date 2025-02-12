package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.ofertas.entities.PrecioEntity

data class PrecioUiState(
    var precio_mas_reciente: PrecioEntity? = null,
    val todos_los_precios:MutableList<PrecioEntity> = mutableStateListOf(),
    val fecha_y_hora: String = "",
)