package samirqb.lib.rrhh.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.entities.AcuerdoDePagoNominaEntity
import samirqb.lib.rrhh.interfaces.IBaseDao

@Dao
interface IAcuerdoDePagoNominaDao: IBaseDao<AcuerdoDePagoNominaEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_acuerdos_de_pago_nomina ORDER BY id_acuerdo_de_pago_pk ASC" )
    fun leerTodo(): Flow<List<AcuerdoDePagoNominaEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_acuerdos_de_pago_nomina WHERE id_acuerdo_de_pago_pk = :id" )
    fun leerPorId(id: Int): Flow<AcuerdoDePagoNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_acuerdos_de_pago_nomina WHERE id_acuerdo_de_pago_pk = :id" )
    fun leerPorId(id: Float): Flow<AcuerdoDePagoNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_acuerdos_de_pago_nomina WHERE id_acuerdo_de_pago_pk = :id" )
    fun leerPorId(id: String): Flow<AcuerdoDePagoNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_acuerdos_de_pago_nomina ORDER BY id_acuerdo_de_pago_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<AcuerdoDePagoNominaEntity?>

    @Query( "DELETE FROM tab_acuerdos_de_pago_nomina" )
    suspend fun borrarTodo()
}