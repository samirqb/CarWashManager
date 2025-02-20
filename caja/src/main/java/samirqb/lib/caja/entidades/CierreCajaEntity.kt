package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_cierres_caja",
    indices = [
        Index(value = ["id_cierre_caja_pk", "id_apertura_caja_fk"]),
        Index(value = ["id_apertura_caja_fk"]),
    ],
    //primaryKeys = ["id_cierre_caja_pk"],
    foreignKeys = [
        ForeignKey(
            entity = AperturaCajaEntity::class,
            parentColumns = ["id_apertura_caja_pk"],
            childColumns = ["id_apertura_caja_fk"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class CierreCajaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_cierre_caja_pk: Int,
    val id_apertura_caja_fk: Int,
    val total_dinero_cierre: Float,
    val fecha_hora_creacion: String,
)