package samirqb.lib.personas.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_operarios",
    indices = [Index(value = ["identificacion_pk"])],
    primaryKeys = ["identificacion_pk"],
)

data class OperarioEntity(
    val identificacion_pk:String,
    val nombre_apellido:String,
    val telefono:String,
    val fecha_hora_creacion:String,
)