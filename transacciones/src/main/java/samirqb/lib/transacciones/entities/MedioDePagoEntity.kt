package samirqb.lib.transacciones.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_medios_de_pagos",
    indices = [Index(value = ["id_medio_de_pago_pk","descripcion"])],
)
data class MedioDePagoEntity(
    @PrimaryKey(autoGenerate = true)
    val id_medio_de_pago_pk:Int,
    val descripcion:String,
    val fecha_hora_creacion:String,
)