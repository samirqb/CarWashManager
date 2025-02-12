package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.personas.entities.OperarioEntity

data class OperarioUiState(
    val todos_los_operarios:MutableList<OperarioEntity> = mutableStateListOf(),
    val todos_los_operarios_activos:MutableList<OperarioEntity> = mutableStateListOf(),
    val todos_los_operarios_inactivos:MutableList<OperarioEntity> = mutableStateListOf(),
    val fecha_y_hora: String = "",
)