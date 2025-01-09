package samirqb.carwashmanager.ofertas.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_servicios",
    indices = [Index(value = ["id_servicio_pk","descripcion"])],
    primaryKeys = ["id_servicio_pk"],
)
data class Servicio(
    val id_servicio_pk:String,
    val descripcion: String,
    val fecha_hora_creacion: String,
)