package samirqb.lib.vehiculos.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_clasificacion_del_vehiculo",
    indices = [Index(value = ["clase_id_pk"])],
    //primaryKeys = ["clase_id_pk"]
)
data class ClasificacionDelVehiculoEntity (
    @PrimaryKey(autoGenerate = true)
    val clase_id_pk: Int,
    val descripcion: String,
    val fecha_hora_creacion: String
    )