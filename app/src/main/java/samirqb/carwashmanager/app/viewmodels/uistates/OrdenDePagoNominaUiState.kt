package samirqb.carwashmanager.app.viewmodels.uistates

import androidx.compose.runtime.mutableStateListOf
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity

data class OrdenDePagoNominaUiState(
    val fecha_y_hora:String = "",
    val numero_de_orden_de_pago: Int = 0,
    val lista_todas_las_ordenes_pago_nomina: MutableList<OrdenPagoNominaEntity> = mutableStateListOf(),
    val lista_ordenes_pago_nomina_pagadas: MutableList<OrdenPagoNominaEntity> = mutableStateListOf(),
    val lista_ordenes_pago_nomina_por_operario_id_pagadas: MutableList<OrdenPagoNominaEntity> = mutableStateListOf(),
    val lista_ordenes_pago_nomina_sin_pagar: MutableList<OrdenPagoNominaEntity> = mutableStateListOf(),
    val lista_ordenes_pago_nomina_por_operario_id_no_pagadas: MutableList<OrdenPagoNominaEntity> = mutableStateListOf(),
)