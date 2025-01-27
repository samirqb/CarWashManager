package samirqb.lib.ventas.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.interfaces.IBaseDao

@Dao
interface IOrdenDeVentaDao: IBaseDao<OrdenDeVentaEntity> {

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_ventas ORDER BY id_orden_pk DESC")
    fun leerTodo(): Flow<List<OrdenDeVentaEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_ordenes_de_ventas WHERE id_orden_pk = :id" )
    fun leerPorId(id: Int): Flow<OrdenDeVentaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_ordenes_de_ventas WHERE id_orden_pk = :id " )
    fun leerPorId(id: Float): Flow<OrdenDeVentaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_ordenes_de_ventas WHERE id_orden_pk = :id" )
    fun leerPorId(id: String): Flow<OrdenDeVentaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_ordenes_de_ventas ORDER BY id_orden_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<OrdenDeVentaEntity?>

    @Query( "DELETE FROM tab_ordenes_de_ventas" )
    suspend fun borrarTodo()

}