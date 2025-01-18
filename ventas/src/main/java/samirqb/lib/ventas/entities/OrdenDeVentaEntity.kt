package samirqb.lib.ventas.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.vehiculos.entities.VehiculoEntity

@Entity(
    tableName = "tab_ordenes_de_ventas",
    indices = [Index(value = ["id_orden_pk","cliente_id_fk","matricula_vehiculo_fk"])],
    foreignKeys = [
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = ["identificacion_pk"],
            childColumns = ["cliente_identificacion_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),

        ForeignKey(
            entity = VehiculoEntity::class,
            parentColumns = ["matricula_pk"],
            childColumns = ["matricula_vehiculo_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
        ),

    ]
)
data class OrdenDeVentaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_orden_pk: Int,
    val cliente_identificacion_fk:String, // esta clave proviene de otra base de datos
    val matricula_vehiculo_fk: String, // esta clave proviene de otra base de datos
    val valor_total_orden: Float,
    val valor_total_solo_servicios: Float,
    val valor_total_solo_productos: Float,
    val fecha_hora_creacion: String,
)