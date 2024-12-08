package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_denominaciones_de_moneda",
    indices = [Index(value = ["id_denominacion_pk","denominacion",])],
)
data class DenominacionMonedaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_denominacion_pk: Int,
    val denominacion: Float,
    val fecha_hora_creacion: String,
)