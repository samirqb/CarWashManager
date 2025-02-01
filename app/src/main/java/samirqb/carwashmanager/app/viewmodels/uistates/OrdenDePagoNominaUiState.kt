package samirqb.carwashmanager.app.viewmodels.uistates

import samirqb.lib.pagos.entities.OrdenPagoNominaEntity

data class OrdenDePagoNominaUiState(
    val fecha_y_hora:String = "",
    val lista_todas_las_ordenes_pago_nomina: List<OrdenPagoNominaEntity> = listOf(),
    val lista_ordenes_pago_nomina_pagadas: List<OrdenPagoNominaEntity> = listOf(),
    val lista_ordenes_pago_nomina_sin_pagar: List<OrdenPagoNominaEntity> = listOf(),
)