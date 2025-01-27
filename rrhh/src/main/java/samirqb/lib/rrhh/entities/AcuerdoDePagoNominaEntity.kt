package samirqb.lib.rrhh.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tab_acuerdos_de_pago_nomina",
    indices = [Index(
        value = ["id_acuerdo_de_pago_pk", "id_plan_compensacion_fk", "id_periodo_facturacion_fk", "id_moneda_fk"]
    )],
    foreignKeys = [
        ForeignKey(
            entity = PlanDeCompensacionEconomicaEntity::class,
            parentColumns = ["id_plan_compensacion_pk"],
            childColumns = ["id_plan_compensacion_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),

        ForeignKey(
            entity = PeriodoFacturacionNominaEntity::class,
            parentColumns = ["id_periodo_facturacion_pk"],
            childColumns = ["id_periodo_facturacion_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
    ]
)
data class AcuerdoDePagoNominaEntity(
    @PrimaryKey(autoGenerate = true) val id_acuerdo_de_pago_pk: Int,
    val titulo_de_acuerdo: String,
    val id_plan_compensacion_fk: Int,
    val id_periodo_facturacion_fk: Int,
    val id_moneda_fk: Int,
    val porcentaje_o_cifra_a_pagar: Float,
    val acuerdo_vigente: Boolean,
    val fecha_hora_creacion: String,
)