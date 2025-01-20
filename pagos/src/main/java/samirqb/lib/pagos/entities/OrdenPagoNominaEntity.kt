package samirqb.lib.pagos.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.personas.entities.OperarioEntity

@Entity(
    tableName = "tab_ordenes_de_pago_nomina",
    indices = [Index(value = ["id_orden_pago_nomina_pk","operario_id_fk","id_moneda_fk"])],
    foreignKeys = [
        ForeignKey(
            entity = OperarioEntity::class,
            parentColumns = ["identificacion_pk"],
            childColumns = ["operario_id_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),

        ForeignKey(
            entity = MonedaEntity::class,
            parentColumns = ["id_moneda_pk"],
            childColumns = ["id_moneda_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),
    ]
)
data class OrdenPagoNominaEntity(
    val id_orden_pago_nomina_pk: Int,
    val operario_id_fk: Int,
    val id_moneda_fk: Int,
    val cantidad_servicios_realizados: Int,
    val total_bruto_ventas_servicios_realizadas: Float,
    val comisiones_por_venta_servicios: Float,
    val cantidad_productos_vendidos: Int,
    val total_bruto_ventas_productos_vendidos: Float,
    val comisiones_por_venta_productos: Float,
    val valor_total_neto_a_pagar: Float,
    val cant_horas_extras_diurnas: Int,
    val valor_total_horas_extras_diurnas: Float,
    val cant_horas_extras_nocturnas: Int,
    val valor_total_horas_extras_nocturnas: Float,
    val salario_base: Float,
    val bonificaciones: Float,
    val orden_pagada: Boolean,
    val fecha_hora_creacion: String,
)