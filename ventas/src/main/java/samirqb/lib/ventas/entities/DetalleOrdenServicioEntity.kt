package samirqb.lib.ventas.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import samirqb.lib.ofertas.entities.PrecioDeServicioEntity
import samirqb.lib.personas.entities.OperarioEntity

@Entity(
    tableName = "tab_detalles_ordenes_servicios",
    indices = [Index(value = ["id_servicio_pk","id_orden_venta_fk","id_precio_y_servicio_fk","id_operario_fk"])],
    foreignKeys = [
        ForeignKey(
            entity = OrdenDeVentaEntity::class,
            parentColumns = ["id_orden_pk"],
            childColumns = ["id_orden_venta_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),

        ForeignKey(
            entity = PrecioDeServicioEntity::class,
            parentColumns = ["id_registro"],
            childColumns = ["id_precio_y_servicio_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),

        ForeignKey(
            entity = OperarioEntity::class,
            parentColumns = ["identificacion_pk"],
            childColumns = ["id_operario_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
    ]
)
data class DetalleOrdenServicioEntity (
    @PrimaryKey(autoGenerate = true)
    val id_registro_pk: Int,
    val id_orden_venta_fk: Int,
    val id_precio_y_servicio_fk: Int,
    val id_operario_fk: String,
    val servicio_culminado: Boolean,
    val fecha_hora_creacion: String,
)