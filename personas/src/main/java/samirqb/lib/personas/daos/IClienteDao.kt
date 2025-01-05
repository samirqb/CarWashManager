package samirqb.lib.personas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.interfaces.IBaseDao

@Dao
interface IClienteDao: IBaseDao<ClienteEntity>{

    @Transaction
    @Query( "SELECT * FROM tab_clientes ORDER BY identificacion_pk ASC" )
    fun leerTodo(): Flow<List<ClienteEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_clientes WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: Int): Flow<ClienteEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clientes WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: Float): Flow<ClienteEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clientes WHERE identificacion_pk = :id ORDER BY identificacion_pk ASC" )
    fun leerPorId(id: String): Flow<ClienteEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clientes ORDER BY identificacion_pk DESC LIMIT 1 " )
    fun leerClienteMasReciente(): Flow<ClienteEntity?>

    @Query( "DELETE FROM tab_clientes" )
    suspend fun borrarTodo()
}