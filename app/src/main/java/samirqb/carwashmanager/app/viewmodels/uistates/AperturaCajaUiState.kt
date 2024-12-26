package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.carwashmanager.app.viewmodels.viewdtos.DetalleACCajaDto
import samirqb.lib.caja.entidades.AperturaCajaEntity

data class AperturaCajaUiState(
    val fecha_y_hora_creacion: String = "",
    val fecha: String = "",
    val hora: String = "",
    val lista_aperturas_caja: List<AperturaCajaEntity> = mutableListOf(),
)