package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.DetalleCierreCajaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IDetalleCierreCajaDao
    : IBaseDao<DetalleCierreCajaEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarVarios( vararg mTEntity: DetalleCierreCajaEntity)

    @Query( "SELECT * FROM tab_detalles_cierre_caja ORDER BY id_cierre_caja_fk DESC" )
    fun leerTodo(): Flow<List<DetalleCierreCajaEntity>>

    @Query( "SELECT * FROM tab_detalles_cierre_caja WHERE id_cierre_caja_fk = :id ORDER BY id_cierre_caja_fk DESC" )
    fun leerPorId(id: Int): Flow<DetalleCierreCajaEntity>

    @Query( "SELECT * FROM tab_detalles_cierre_caja WHERE id_cierre_caja_fk = :id ORDER BY id_cierre_caja_fk DESC" )
    fun leerPorId(id: Float): Flow<DetalleCierreCajaEntity>

    @Query( "SELECT * FROM tab_detalles_cierre_caja WHERE id_cierre_caja_fk = :id ORDER BY id_cierre_caja_fk DESC" )
    fun leerPorId(id: String): Flow<DetalleCierreCajaEntity>

    @Query( "DELETE FROM tab_detalles_cierre_caja" )
    suspend fun borrarTodo()
}