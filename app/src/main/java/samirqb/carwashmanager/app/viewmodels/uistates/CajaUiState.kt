package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto

data class CajaUiState (
    val fecha_y_hora: String = "",
    val fecha: String = "",
    val hora: String = "",
    val id_apertura_caja: Int = 0,
    //val suma_total_todas_las_monedas:MutableState<Float> = mutableStateOf(0f),
    //val suma_total_todas_las_monedas:Float = 0f,
    //val suma_total_todas_las_monedas: MutableFloatState = mutableFloatStateOf(0f),
    //val lista_detalles_ac_caja_dtos: MutableList<DetalleACCajaDto> = mutableListOf(),
    //val apertura_caja_status: MutableState<Boolean> = mutableStateOf(false)
)
