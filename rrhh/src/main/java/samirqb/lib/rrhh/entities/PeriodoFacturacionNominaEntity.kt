package samirqb.lib.rrhh.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_periodos_de_facturacion_nomina",
    indices = [Index(value = ["id_periodo_facturacion_pk","descripcion_del_periodo"])],
)
data class PeriodoFacturacionNominaEntity(
    @PrimaryKey(autoGenerate = true)
    val id_periodo_facturacion_pk: Int,
    val descripcion_del_periodo:String,
    val fecha_hora_creacion:String,
)