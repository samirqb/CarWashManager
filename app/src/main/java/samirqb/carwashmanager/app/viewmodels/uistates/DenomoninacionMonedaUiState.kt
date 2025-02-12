package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.caja.entidades.DenominacionMonedaEntity

data class DenomoninacionMonedaUiState(
    val mDenominacionMonedaEntity: DenominacionMonedaEntity = DenominacionMonedaEntity(
        denominacion_pk = 0f,
        fecha_hora_creacion = "",
    ),

    val todasLasDenominacionesMoneda: MutableList<DenominacionMonedaEntity> = mutableStateListOf()
)