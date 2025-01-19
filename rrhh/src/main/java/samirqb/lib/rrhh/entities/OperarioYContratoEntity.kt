package samirqb.lib.rrhh.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import samirqb.lib.personas.entities.OperarioEntity

@Entity(
    tableName = "tab_operarios_y_contratos",
    indices = [Index(value = ["id_contrato_pk","identificacion_operario","id_acuerdo_de_pago_fk"])],
    foreignKeys = [
        ForeignKey(
            entity = OperarioEntity::class,
            parentColumns = ["identificacion_pk"],
            childColumns = ["identificacion_operario"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),

        ForeignKey(
            entity = AcuerdoDePagoNominaEntity::class,
            parentColumns = ["id_acuerdo_de_pago_pk"],
            childColumns = ["id_acuerdo_de_pago_fk"],
            onDelete = CASCADE,
            onUpdate = CASCADE,
        ),
    ]
)
data class OperarioYContratoEntity(
    @PrimaryKey(autoGenerate = true)
    val id_contrato_pk:Int,
    val identificacion_operario:String,
    val id_acuerdo_de_pago_fk: Int,
    val contrato_vigente: Boolean,
    val fecha_hora_creacion: String,
)