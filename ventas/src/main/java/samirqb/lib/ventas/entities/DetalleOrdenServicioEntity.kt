package samirqb.lib.ventas.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_detalles_ordenes_servicios",
    indices = [
        Index(value = ["id_registro_pk","id_orden_pago_nomina_fk","id_precio_y_servicio_fk","id_operario_fk"]),
        Index(value = ["id_orden_venta_fk"])
              ],
    foreignKeys = [
        ForeignKey(
            entity = OrdenDeVentaEntity::class,
            parentColumns = ["id_orden_pk"],
            childColumns = ["id_orden_venta_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
    ]
)
data class DetalleOrdenServicioEntity (
    @PrimaryKey(autoGenerate = true)
    val id_registro_pk: Int,
    val id_orden_venta_fk: Int,
    val id_orden_pago_nomina_fk: Int,
    val id_precio_y_servicio_fk: Int,
    val id_operario_fk: String,
    val servicio_culminado: Boolean,
    val fecha_hora_creacion: String,
)