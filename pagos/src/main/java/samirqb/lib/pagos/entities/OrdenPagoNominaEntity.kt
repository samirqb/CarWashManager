package samirqb.lib.pagos.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_ordenes_de_pago_nomina",
    indices = [Index(value = ["id_orden_pago_nomina_pk","operario_id_fk"])],
    //foreignKeys = [ ]
)
data class OrdenPagoNominaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_orden_pago_nomina_pk: Int,
    val operario_id_fk: String,
    val orden_vigente: Boolean,
    val fecha_hora_creacion: String,
)