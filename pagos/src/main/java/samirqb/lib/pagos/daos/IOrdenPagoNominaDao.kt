package samirqb.lib.pagos.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.interfaces.IBaseDao

@Dao
interface IOrdenPagoNominaDao : IBaseDao<OrdenPagoNominaEntity> {
    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina ORDER BY id_orden_pago_nomina_pk DESC")
    fun leerTodo(): Flow<List<OrdenPagoNominaEntity>>

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina WHERE id_orden_pago_nomina_pk = :id")
    fun leerPorId(id: Int): Flow<OrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina WHERE id_orden_pago_nomina_pk = :id ")
    fun leerPorId(id: Float): Flow<OrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina WHERE id_orden_pago_nomina_pk = :id")
    fun leerPorId(id: String): Flow<OrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina ORDER BY id_orden_pago_nomina_pk DESC LIMIT 1 ")
    fun leerMasReciente(): Flow<OrdenPagoNominaEntity?>

    @Query("DELETE FROM tab_ordenes_de_pago_nomina")
    suspend fun borrarTodo()

    //CUSTOM
    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina WHERE orden_vigente = :orden_vigente")
    fun leerPorOrdenPagada(orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>>

    @Transaction
    @Query("SELECT * FROM tab_ordenes_de_pago_nomina WHERE operario_id_fk = :operario_id AND orden_vigente = :orden_vigente ORDER BY id_orden_pago_nomina_pk DESC")
    fun leerPorOperarioIdYOrdenVigente(operario_id: String, orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>>

    @Query( "SELECT COUNT(id_orden_pago_nomina_pk) FROM tab_ordenes_de_pago_nomina" )
    fun obtenerCantidadDeRegistros(): Flow<Int>
}