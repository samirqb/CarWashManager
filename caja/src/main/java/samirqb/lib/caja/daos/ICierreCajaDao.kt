package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.CierreCajaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface ICierreCajaDao: IBaseDao<CierreCajaEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_cierres_caja ORDER BY id_cierre_caja_pk ASC" )
    fun leerTodo(): Flow<List<CierreCajaEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_cierres_caja WHERE id_cierre_caja_pk = :id ORDER BY id_cierre_caja_pk ASC" )
    fun leerPorId(id: Int): Flow<CierreCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_cierres_caja WHERE id_cierre_caja_pk = :id ORDER BY id_cierre_caja_pk ASC" )
    fun leerPorId(id: Float): Flow<CierreCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_cierres_caja WHERE id_cierre_caja_pk = :id ORDER BY id_cierre_caja_pk ASC" )
    fun leerPorId(id: String): Flow<CierreCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_cierres_caja ORDER BY id_cierre_caja_pk DESC LIMIT 1 " )
    fun leerCierreCajaMasReciente(): Flow<CierreCajaEntity?>

    @Query( "DELETE FROM tab_cierres_caja" )
    suspend fun borrarTodo()
}