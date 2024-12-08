package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_monedas",
    indices = [Index(value = ["id_moneda_pk","codigo_iso_4217_fk","id_denominacion_fk","tipo_fk",])],
    foreignKeys = [
        ForeignKey(
            entity = UnidadMonetariaEntity::class,
            parentColumns = ["codigo_iso_4217_pk"],
            childColumns = ["codigo_iso_4217_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
        ForeignKey(
            entity = DenominacionMonedaEntity::class,
            parentColumns = ["id_denominacion_pk"],
            childColumns = ["id_denominacion_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
        ForeignKey(
            entity = TipoMonedaEntity::class,
            parentColumns = ["tipo_pk"],
            childColumns = ["tipo_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
    ]
)
data class MonedaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_moneda_pk:Int,
    val codigo_iso_4217_fk:String,
    val id_denominacion_fk:Int,
    val tipo_fk:String,
    val fecha_hora_creacion:String,
)