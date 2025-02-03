package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.vehiculos.entities.VehiculoEntity

data class VehiculoUiState(
    val fecha_y_hora: String = "",
    val listar_todos_los_vehiculos:List<VehiculoEntity> = listOf(),
    val resultado_busqueda_vehiculo:VehiculoEntity? = null,
)