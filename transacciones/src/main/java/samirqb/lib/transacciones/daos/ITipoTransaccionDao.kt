package samirqb.lib.transacciones.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.entities.TipoTransaccionEntity
import samirqb.lib.transacciones.interfaces.IBaseDao

@Dao
interface ITipoTransaccionDao: IBaseDao<TipoTransaccionEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_tipos_de_transacciones ORDER BY id_tipo_transaccion_pk ASC" )
    fun leerTodo(): Flow<List<TipoTransaccionEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_tipos_de_transacciones WHERE id_tipo_transaccion_pk = :id" )
    fun leerPorId(id: Int): Flow<TipoTransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_tipos_de_transacciones WHERE id_tipo_transaccion_pk = :id" )
    fun leerPorId(id: Float): Flow<TipoTransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_tipos_de_transacciones WHERE id_tipo_transaccion_pk = :id" )
    fun leerPorId(id: String): Flow<TipoTransaccionEntity>

    @Transaction
    @Query( "SELECT * FROM tab_tipos_de_transacciones ORDER BY id_tipo_transaccion_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<TipoTransaccionEntity?>

    @Query( "DELETE FROM tab_tipos_de_transacciones" )
    suspend fun borrarTodo()

}