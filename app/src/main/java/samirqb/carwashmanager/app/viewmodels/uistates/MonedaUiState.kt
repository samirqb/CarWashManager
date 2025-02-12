package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.caja.entidades.MonedaEntity

data class MonedaUiState(
    val fecha_hora_creacion:String = "",

    val mMonedaEntity: MonedaEntity = MonedaEntity(
        id_moneda_pk = 0,
        codigo_iso_4217_fk = "",
        denominacion_fk = 0f,
        tipo_fk = "",
        fecha_hora_creacion = "",
    ),

    val todasLasMonedas: MutableList<MonedaEntity> = mutableStateListOf()
)