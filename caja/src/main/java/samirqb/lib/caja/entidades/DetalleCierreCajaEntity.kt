package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_detalles_cierre_caja",
    indices = [Index(value = ["id_registro_detalle_pk","id_cierre_caja_fk","id_moneda_fk",])],
    foreignKeys = [
        ForeignKey(
            entity = CierreCajaEntity::class,
            parentColumns = ["id_cierre_caja_pk"],
            childColumns = ["id_cierre_caja_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = MonedaEntity::class,
            parentColumns = ["id_moneda_pk"],
            childColumns = ["id_moneda_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
    ]
)
data class DetalleCierreCajaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_registro_detalle_pk:Int,
    val id_cierre_caja_fk:Int,
    val id_moneda_fk:Int,
    val cantidad_unidades_de_la_deniminacion:Int,
    val monto_total_de_la_denominacion:Float,
    val fecha_hora_creacion:String,
)
