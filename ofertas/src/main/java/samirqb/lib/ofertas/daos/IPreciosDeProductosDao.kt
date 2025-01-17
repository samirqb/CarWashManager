package samirqb.lib.ofertas.daos

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PrecioDeProductoEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

interface IPreciosDeProductosDao:IBaseDao<PrecioDeProductoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<PrecioDeProductoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<PrecioDeProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<PrecioDeProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<PrecioDeProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PrecioDeProductoEntity?>

    @Query( "DELETE FROM tab_precios_de_productos" )
    suspend fun borrarTodo()

}