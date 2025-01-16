package samirqb.lib.ofertas.daos

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.PreciosDeProductosEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

interface IPreciosDeProductosDao:IBaseDao<PreciosDeProductosEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<PreciosDeProductosEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<PreciosDeProductosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<PreciosDeProductosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<PreciosDeProductosEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_productos ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PreciosDeProductosEntity?>

    @Query( "DELETE FROM tab_precios_de_productos" )
    suspend fun borrarTodo()

}