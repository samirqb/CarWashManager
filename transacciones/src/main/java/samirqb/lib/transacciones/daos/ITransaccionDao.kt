package samirqb.lib.transacciones.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.entities.TransaccionEntity
import samirqb.lib.transacciones.interfaces.IBaseDao

@Dao
interface ITransaccionDao: IBaseDao<TransaccionEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_transacciones ORDER BY id_transaccion_pk ASC" )
    fun leerTodo(): Flow<List<TransaccionEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_transacciones WHERE id_transaccion_pk = :id" )
    fun leerPorId(id: Int): Flow<TransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_transacciones WHERE id_transaccion_pk = :id" )
    fun leerPorId(id: Float): Flow<TransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_transacciones WHERE id_transaccion_pk = :id" )
    fun leerPorId(id: String): Flow<TransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_transacciones ORDER BY id_transaccion_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<TransaccionEntity?>

    @Query( "DELETE FROM tab_transacciones" )
    suspend fun borrarTodo()

}