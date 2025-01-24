package samirqb.lib.ofertas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PrecioDeServicioEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

@Dao
interface IPrecioDeServicioDao:IBaseDao<PrecioDeServicioEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<PrecioDeServicioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<PrecioDeServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<PrecioDeServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<PrecioDeServicioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PrecioDeServicioEntity?>

    @Query( "DELETE FROM tab_precios_de_servicios" )
    suspend fun borrarTodo()
    
}