package samirqb.lib.rrhh.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_planes_de_compensacion_economica",
    indices = [Index(value = ["id_plan_compensacion_pk","descripcion_plan_compensacion"])]
)
data class PlanDeCompensacionEconomicaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_plan_compensacion_pk:Int,
    val descripcion_plan_compensacion: String,
    val fecha_hora_creacion: String,
)