package samirqb.lib.ofertas.daos

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PreciosDeServiciosEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

interface IPreciosDeServiciosDao:IBaseDao<PreciosDeServiciosEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<PreciosDeServiciosEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<PreciosDeServiciosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<PreciosDeServiciosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<PreciosDeServiciosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PreciosDeServiciosEntity?>

    @Query( "DELETE FROM tab_precios_de_servicios" )
    suspend fun borrarTodo()
    
}