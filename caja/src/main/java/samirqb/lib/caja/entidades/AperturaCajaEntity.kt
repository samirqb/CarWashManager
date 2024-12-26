package samirqb.lib.caja.entidades

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_aperturas_caja",
    indices = [Index(value = ["id_apertura_caja_pk"])],
)
data class AperturaCajaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_apertura_caja_pk:Int,
    val total_dinero_apertura:Float,
    val fecha_hora_creacion:String,
    val apertura_activa:Boolean,
)