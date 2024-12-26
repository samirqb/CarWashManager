package samirqb.carwashmanager.app.viewmodels.viewdtos

data class DetalleACCajaDto(
    var id_ac_caja: Int,
    var id_moneda: Int,
    var cant_unidades_de_la_denominacion: Int,
    var monto_total_de_la_denominacion: Float,
    var fecha_hora_creacion: String,
)