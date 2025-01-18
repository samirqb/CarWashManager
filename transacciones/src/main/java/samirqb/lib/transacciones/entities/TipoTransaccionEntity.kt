package samirqb.lib.transacciones.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_tipos_de_transacciones",
    indices = [Index(value = ["id_tipo_transaccion_pk","descripcion"])],
)
data class TipoTransaccionEntity(
    @PrimaryKey(autoGenerate = true)
    val id_tipo_transaccion_pk: Int,
    val descripcion: String,
    val fecha_hora_creacion:String,
)