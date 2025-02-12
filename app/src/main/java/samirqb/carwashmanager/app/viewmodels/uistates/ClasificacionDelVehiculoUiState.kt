package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity

data class ClasificacionDelVehiculoUiState(
    val fecha_y_hora:String = "",
    val listar_todas_las_clasificaciones_de_vehiculo:MutableList<ClasificacionDelVehiculoEntity> = mutableStateListOf(),
)