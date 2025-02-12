package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.ofertas.entities.ServicioEntity

data class ServicioUiState(
    val todos_los_servicios:MutableList<ServicioEntity> = mutableStateListOf(),
    val fecha_y_hora: String = "",
)