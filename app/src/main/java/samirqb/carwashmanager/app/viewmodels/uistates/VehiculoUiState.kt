package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.vehiculos.entities.VehiculoEntity

data class VehiculoUiState(
    val matricala_value_buscado: String = "",
    val fecha_y_hora: String = "",
    val listar_todos_los_vehiculos:MutableList<VehiculoEntity> = mutableStateListOf(),
    val resultado_busqueda_vehiculo:VehiculoEntity? = null,
)