package samirqb.lib.ventas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.interfaces.IBaseDao

@Dao
interface IDetalleOrdenProductoDao: IBaseDao<DetalleOrdenProductoEntity> {

    @Transaction
    @Query("SELECT * FROM tab_detalles_ordenes_productos ORDER BY id_registro_pk ASC")
    fun leerTodo(): Flow<List<DetalleOrdenProductoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_productos WHERE id_registro_pk = :id" )
    fun leerPorId(id: Int): Flow<DetalleOrdenProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_productos WHERE id_registro_pk = :id " )
    fun leerPorId(id: Float): Flow<DetalleOrdenProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_productos WHERE id_registro_pk = :id" )
    fun leerPorId(id: String): Flow<DetalleOrdenProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_productos ORDER BY id_registro_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<DetalleOrdenProductoEntity?>

    @Query( "DELETE FROM tab_detalles_ordenes_productos" )
    suspend fun borrarTodo()
    
}