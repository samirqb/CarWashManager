package samirqb.lib.ventas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.interfaces.IBaseDao

@Dao
interface IDetalleOrdenServicioDao: IBaseDao<DetalleOrdenServicioEntity> {

    @Transaction
    @Query("SELECT * FROM tab_detalles_ordenes_servicios ORDER BY id_registro_pk ASC")
    fun leerTodo(): Flow<List<DetalleOrdenServicioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_servicios WHERE id_registro_pk = :id" )
    fun leerPorId(id: Int): Flow<DetalleOrdenServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_servicios WHERE id_registro_pk = :id " )
    fun leerPorId(id: Float): Flow<DetalleOrdenServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_servicios WHERE id_registro_pk = :id" )
    fun leerPorId(id: String): Flow<DetalleOrdenServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_detalles_ordenes_servicios ORDER BY id_registro_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<DetalleOrdenServicioEntity?>

    @Query( "DELETE FROM tab_detalles_ordenes_servicios" )
    suspend fun borrarTodo()

}