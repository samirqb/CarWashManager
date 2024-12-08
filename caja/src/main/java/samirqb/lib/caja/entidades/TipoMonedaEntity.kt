package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_tipos_de_moneda",
    indices = [Index(value = ["tipo_pk"])],
    primaryKeys = ["tipo_pk"]
)
data class TipoMonedaEntity(
    val tipo_pk:String,
    val fecha_hora_creacion:String,
)