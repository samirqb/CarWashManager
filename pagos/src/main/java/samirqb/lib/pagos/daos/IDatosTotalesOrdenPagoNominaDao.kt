package samirqb.lib.pagos.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.entities.DatosTotalesOrdenPagoNominaEntity
import samirqb.lib.pagos.interfaces.IBaseDao

@Dao
interface IDatosTotalesOrdenPagoNominaDao:IBaseDao<DatosTotalesOrdenPagoNominaEntity> {
    @Transaction
    @Query("SELECT * FROM tab_datos_totales_ordenes_de_pago_nomina ORDER BY id_datos_totales_orden_pago_nomina_pk DESC")
    fun leerTodo(): Flow<List<DatosTotalesOrdenPagoNominaEntity>>

    @Transaction
    @Query("SELECT * FROM tab_datos_totales_ordenes_de_pago_nomina WHERE id_datos_totales_orden_pago_nomina_pk = :id")
    fun leerPorId(id: Int): Flow<DatosTotalesOrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_datos_totales_ordenes_de_pago_nomina WHERE id_datos_totales_orden_pago_nomina_pk = :id ")
    fun leerPorId(id: Float): Flow<DatosTotalesOrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_datos_totales_ordenes_de_pago_nomina WHERE id_datos_totales_orden_pago_nomina_pk = :id")
    fun leerPorId(id: String): Flow<DatosTotalesOrdenPagoNominaEntity>

    @Transaction
    @Query("SELECT * FROM tab_datos_totales_ordenes_de_pago_nomina ORDER BY id_datos_totales_orden_pago_nomina_pk DESC LIMIT 1 ")
    fun leerMasReciente(): Flow<DatosTotalesOrdenPagoNominaEntity?>

    @Query("DELETE FROM tab_datos_totales_ordenes_de_pago_nomina")
    suspend fun borrarTodo()
}