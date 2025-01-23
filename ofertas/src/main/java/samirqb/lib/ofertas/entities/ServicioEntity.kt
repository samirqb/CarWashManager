package samirqb.lib.ofertas.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_servicios",
    indices = [Index(value = ["id_servicio_pk","descripcion"])],
    //primaryKeys = ["id_servicio_pk"],
)
data class ServicioEntity(
    @PrimaryKey(autoGenerate = true)
    val id_servicio_pk:Int,
    val descripcion: String,
    val fecha_hora_creacion: String,
)