package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.caja.entidades.DenominacionMonedaEntity

data class DenomoninacionMonedaUiState(
    val mDenominacionMonedaEntity: DenominacionMonedaEntity = DenominacionMonedaEntity(
        denominacion_pk = 0f,
        fecha_hora_creacion = "",
    ),

    val todasLasDenominacionesMoneda: List<DenominacionMonedaEntity> = listOf()
)