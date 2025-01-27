package samirqb.lib.transacciones.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_transacciones",
    indices = [Index(value = ["id_transaccion_pk","id_apertura_caja","id_tipo_transaccion_fk","id_medio_de_pago_fk","id_moneda_fk","id_orden_fk"])],
    foreignKeys = [

        ForeignKey(
            entity = TipoTransaccionEntity::class,
            parentColumns = ["id_tipo_transaccion_pk"],
            childColumns = ["id_tipo_transaccion_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),

        ForeignKey(
            entity = MedioDePagoEntity::class,
            parentColumns = ["id_medio_de_pago_pk"],
            childColumns = ["id_medio_de_pago_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),

    ]
)
data class TransaccionEntity(
    @PrimaryKey(autoGenerate = true)
    val id_transaccion_pk:Int,
    val id_apertura_caja_fk: Int,
    val id_tipo_transaccion_fk: Int,
    val id_moneda_fk: Int,
    val id_medio_de_pago_fk: Int,
    val id_orden_fk: Int,
    val fecha_hora_creaccion: String
)