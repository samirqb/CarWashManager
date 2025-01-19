package samirqb.lib.rrhh.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.entities.OperarioYContratoEntity
import samirqb.lib.rrhh.interfaces.IBaseDao

@Dao
interface IOperarioYContratoDao: IBaseDao<OperarioYContratoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_operarios_y_contratos ORDER BY id_contrato_pk ASC" )
    fun leerTodo(): Flow<List<OperarioYContratoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_operarios_y_contratos WHERE id_contrato_pk = :id" )
    fun leerPorId(id: Int): Flow<OperarioYContratoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios_y_contratos WHERE id_contrato_pk = :id" )
    fun leerPorId(id: Float): Flow<OperarioYContratoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios_y_contratos WHERE id_contrato_pk = :id" )
    fun leerPorId(id: String): Flow<OperarioYContratoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios_y_contratos ORDER BY id_contrato_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<OperarioYContratoEntity?>

    @Query( "DELETE FROM tab_operarios_y_contratos" )
    suspend fun borrarTodo()
}