package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto
import samirqb.lib.caja.entidades.CierreCajaEntity

data class CierreCajaUiState(
    val id_cierre_actual:Int = 0,
    val fecha_y_hora_creacion: String = "",
    val fecha: String = "",
    val hora: String = "",
    val lista_cierres_caja: List<CierreCajaEntity> = mutableListOf(),
    val ultimaAperturaCaja: CierreCajaEntity? = CierreCajaEntity(
        id_cierre_caja_pk = 0,
        id_apertura_caja_fk = 0,
        total_dinero_cierre = 0F,
        fecha_hora_creacion = ""
    ),
    val suma_total_todas_las_monedas: MutableFloatState = mutableFloatStateOf(0f),
    val lista_detalles_ac_caja_dtos: MutableList<DetalleACCajaDto> = mutableListOf(),
)