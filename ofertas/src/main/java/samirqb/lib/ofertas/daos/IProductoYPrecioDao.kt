package samirqb.lib.ofertas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ProductoYPrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

@Dao
interface IProductoYPrecioDao:IBaseDao<ProductoYPrecioEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<ProductoYPrecioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<ProductoYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<ProductoYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<ProductoYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<ProductoYPrecioEntity?>

    @Query( "DELETE FROM tab_precios_de_productos" )
    suspend fun borrarTodo()

}