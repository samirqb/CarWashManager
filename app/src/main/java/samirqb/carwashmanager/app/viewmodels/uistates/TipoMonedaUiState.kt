package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.caja.entidades.TipoMonedaEntity

data class TipoMonedaUiState(

    val mTipoMonedaEntity: TipoMonedaEntity = TipoMonedaEntity(
        tipo_pk = "",
        fecha_hora_creacion = "",
    ),

    val todoslosTiposMoneda: MutableList<TipoMonedaEntity> = mutableStateListOf()
)
