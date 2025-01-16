package samirqb.lib.ofertas.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_precios",
    indices = [Index(value = ["precio_pk"])],
    primaryKeys = ["precio_pk"]
)
data class PrecioEntity (
    val precio_pk:Float,
    val fecha_hora_creacion: String
)