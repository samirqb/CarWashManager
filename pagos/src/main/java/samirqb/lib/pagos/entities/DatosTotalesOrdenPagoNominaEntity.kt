package samirqb.lib.pagos.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_datos_totales_ordenes_de_pago_nomina",
    indices = [
        Index(value = ["id_datos_totales_orden_pago_nomina_pk","id_orden_pago_nomina_fk","id_moneda_fk"]),
        Index(value = ["id_orden_pago_nomina_fk"])
    ],
    foreignKeys = [
        ForeignKey(
            entity =  OrdenPagoNominaEntity::class,
            parentColumns = ["id_orden_pago_nomina_pk"],
            childColumns = ["id_orden_pago_nomina_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        )
    ]
)
data class DatosTotalesOrdenPagoNominaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_datos_totales_orden_pago_nomina_pk: Int,
    val id_orden_pago_nomina_fk: Int,
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
    val fecha_hora_creacion: String,
)