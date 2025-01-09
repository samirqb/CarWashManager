package samirqb.carwashmanager.ofertas.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_precios",
    indices = [Index(value = ["precio_pk"])],
)
data class Precio (
    @PrimaryKey(autoGenerate = true)
    val precio_pk:Int,
    val fecha_hora_creacion: String
)