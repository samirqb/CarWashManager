package samirqb.lib.vehiculos.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    tableName = "tab_vehiculos",
    indices = [Index( value = ["matricula_pk"])],
    primaryKeys = ["matricula_pk"],
    foreignKeys = [
        ForeignKey(
            entity = ClasificacionDelVehiculoEntity::class,
            parentColumns = ["clase_id_pk"],
            childColumns = ["clase_id_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class VehiculoEntity(
    val matricula_pk:String,
    val clase_id_fk: Int,
    val fecha_hora_creacion:String,
)