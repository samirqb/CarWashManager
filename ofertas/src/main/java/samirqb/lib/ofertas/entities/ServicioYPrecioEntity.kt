package samirqb.lib.ofertas.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_precios_de_servicios",
    indices = [
        Index(value = ["id_registro", "id_servicio_fk", "precio_fk", "codigo_iso_4217_fk"]),
        Index(value = ["id_servicio_fk"]),
        Index(value = ["precio_fk"]),
    ],
    foreignKeys = [
        ForeignKey(
            entity = ServicioEntity::class,
            parentColumns = ["id_servicio_pk"],
            childColumns = ["id_servicio_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),
        ForeignKey(
            entity = PrecioEntity::class,
            parentColumns = ["precio_pk"],
            childColumns = ["precio_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        ),
    ]
)
data class ServicioYPrecioEntity(
    @PrimaryKey(autoGenerate = true)
    var id_registro: Int,
    var id_servicio_fk: Int,
    var precio_fk: Float,
    var codigo_iso_4217_fk: String,
    var precio_activo: Boolean,
    var fecha_hora_creacion: String,
)
