package samirqb.lib.personas.entities

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "tab_clientes",
    indices = [Index(value = ["identificacion_pk"])],
    primaryKeys = ["identificacion_pk"],
)

data class ClienteEntity(
    val identificacion_pk:String,
    val nombre_apellidos:String,
    val telefono:String,
    val fecha_hora_creacion:String,
)