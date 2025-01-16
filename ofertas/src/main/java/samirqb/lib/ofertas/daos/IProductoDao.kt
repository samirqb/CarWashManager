package samirqb.lib.ofertas.daos

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ProductoEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

interface IProductoDao:IBaseDao<ProductoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_productos ORDER BY id_producto_pk ASC" )
    fun leerTodo(): Flow<List<ProductoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_productos WHERE id_producto_pk = :id ORDER BY id_producto_pk ASC" )
    fun leerPorId(id: Int): Flow<ProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_productos WHERE id_producto_pk = :id ORDER BY id_producto_pk ASC" )
    fun leerPorId(id: Float): Flow<ProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_productos WHERE id_producto_pk = :id ORDER BY id_producto_pk ASC" )
    fun leerPorId(id: String): Flow<ProductoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_productos ORDER BY id_producto_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<ProductoEntity?>

    @Query( "DELETE FROM tab_productos" )
    suspend fun borrarTodo()
    
}