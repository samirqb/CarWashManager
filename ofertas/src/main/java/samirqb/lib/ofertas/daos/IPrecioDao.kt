package samirqb.lib.ofertas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

@Dao
interface IPrecioDao: IBaseDao<PrecioEntity> {
    @Transaction
    @Query( "SELECT * FROM tab_precios ORDER BY precio_pk ASC" )
    fun leerTodo(): Flow<List<PrecioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios WHERE precio_pk = :id ORDER BY precio_pk ASC" )
    fun leerPorId(id: Int): Flow<PrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios WHERE precio_pk = :id ORDER BY precio_pk ASC" )
    fun leerPorId(id: Float): Flow<PrecioEntity>

    @Transaction
    //@Query( "SELECT * FROM tab_precios WHERE precio_pk = :id ORDER BY precio_pk ASC" )
    @Query( "SELECT LAST_INSERT_ROWID()" )
    fun leerPorId(id: String): Flow<PrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios ORDER BY precio_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PrecioEntity?>

    @Query( "DELETE FROM tab_precios" )
    suspend fun borrarTodo()
}