package samirqb.lib.personas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.OperarioEntity
import samirqb.lib.personas.interfaces.IBaseDao

@Dao
interface IOperarioDao: IBaseDao<OperarioEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_operarios ORDER BY identificacion_pk ASC" )
    fun leerTodo(): Flow<List<OperarioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_operarios WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: Int): Flow<OperarioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: Float): Flow<OperarioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: String): Flow<OperarioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_operarios ORDER BY identificacion_pk DESC LIMIT 1 " )
    fun leerClienteMasReciente(): Flow<OperarioEntity?>

    @Query( "DELETE FROM tab_clientes" )
    suspend fun borrarTodo()

    //CUSTOM
    @Transaction
    @Query( "SELECT * FROM tab_operarios WHERE operario_activo = :operario_activo ORDER BY identificacion_pk ASC" )
    fun leerPorOperarioActivo(operario_activo: Boolean): Flow<List<OperarioEntity>>

}