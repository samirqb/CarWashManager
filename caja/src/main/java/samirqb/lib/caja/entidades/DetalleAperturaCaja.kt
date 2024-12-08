package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_detalles_aperturas_caja",
    indices = [Index(value = ["id_registro_detalle","id_apertura_caja_fk","id_moneda_fk",])],
    foreignKeys = [
        ForeignKey(
            entity = AperturaCaja::class,
            parentColumns = ["id_apertura_caja_pk"],
            childColumns = ["id_apertura_caja_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MonedaEntity::class,
            parentColumns = ["id_moneda_pk"],
            childColumns = ["id_moneda_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE),
    ]
)
data class DetalleAperturaCaja(
    @PrimaryKey(autoGenerate = true)
    val id_registro_detalle:Int,
    val id_apertura_caja_fk:Int,
    val id_moneda_fk:Int,
    val cantidad_unidades_de_la_deniminacion:Int,
    val monto_total_de_la_denominacion:Int,
    val fecha_hora_creacion:Int,
)