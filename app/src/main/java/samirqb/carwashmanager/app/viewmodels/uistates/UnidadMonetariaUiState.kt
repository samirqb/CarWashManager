package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.StateFlow
import samirqb.lib.caja.entidades.UnidadMonetariaEntity

data class UnidadMonetariaUiState(

    val mUnidadMonetariaEntity: UnidadMonetariaEntity = UnidadMonetariaEntity(
        codigo_iso_4217_pk = "",
        nombre_y_origen = "",
        fecha_hora_creacion = "",
    ),

    val lista_unidades_monetarias: List<UnidadMonetariaEntity> = listOf(),
)