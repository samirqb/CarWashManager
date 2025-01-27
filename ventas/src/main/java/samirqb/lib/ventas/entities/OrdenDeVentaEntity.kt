package samirqb.lib.ventas.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_ordenes_de_ventas",
    indices = [Index(value = ["id_orden_pk","cliente_identificacion_fk","matricula_vehiculo_fk"])],
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