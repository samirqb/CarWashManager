package samirqb.lib.ofertas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

@Dao
interface IServicioDao:IBaseDao<ServicioEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_servicios ORDER BY id_servicio_pk ASC" )
    fun leerTodo(): Flow<List<ServicioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_servicios WHERE id_servicio_pk = :id ORDER BY id_servicio_pk ASC" )
    fun leerPorId(id: Int): Flow<ServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_servicios WHERE id_servicio_pk = :id ORDER BY id_servicio_pk ASC" )
    fun leerPorId(id: Float): Flow<ServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_servicios WHERE id_servicio_pk = :id ORDER BY id_servicio_pk ASC" )
    fun leerPorId(id: String): Flow<ServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_servicios ORDER BY id_servicio_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<ServicioEntity?>

    @Query( "DELETE FROM tab_servicios" )
    suspend fun borrarTodo()

}