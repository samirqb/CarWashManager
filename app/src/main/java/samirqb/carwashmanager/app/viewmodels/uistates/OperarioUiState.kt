package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.personas.entities.OperarioEntity

data class OperarioUiState(
    val todos_los_operarios:List<OperarioEntity> = listOf(),
    val fecha_y_hora: String = "",
)