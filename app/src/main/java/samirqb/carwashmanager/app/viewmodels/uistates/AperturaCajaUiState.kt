package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.caja.entidades.AperturaCajaEntity

data class AperturaCajaUiState(
    val id_apertura_actual:Int = 0,
    val fecha_y_hora_creacion: String = "",
    val fecha: String = "",
    val hora: String = "",
    val lista_aperturas_caja: List<AperturaCajaEntity> = mutableListOf(),
    val ultimaAperturaCaja: AperturaCajaEntity? = AperturaCajaEntity(
        id_apertura_caja_pk = 0,
        total_dinero_apertura = 0f,
        fecha_hora_creacion = "",
        apertura_activa = false
    ),
)