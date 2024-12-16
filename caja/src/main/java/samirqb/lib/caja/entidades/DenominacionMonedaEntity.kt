package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["denominacion_pk"],
    tableName = "tab_denominaciones_de_moneda",
    indices = [Index(value = [
        //"id_denominacion_pk",
        "denominacion_pk",])],
)
data class DenominacionMonedaEntity(
    //@PrimaryKey(autoGenerate = true)
    //val id_denominacion_pk: Int = 0,
    val denominacion_pk: Float,
    val fecha_hora_creacion: String,
)