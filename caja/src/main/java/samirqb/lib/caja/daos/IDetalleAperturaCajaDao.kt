package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.DetalleAperturaCajaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IDetalleAperturaCajaDao: IBaseDao<DetalleAperturaCajaEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarVarios( vararg mTEntity: DetalleAperturaCajaEntity)

    @Query( "SELECT * FROM tab_detalles_aperturas_caja ORDER BY id_apertura_caja_fk DESC" )
    fun leerTodo(): Flow<List<DetalleAperturaCajaEntity>>

    @Query( "SELECT * FROM tab_detalles_aperturas_caja WHERE id_apertura_caja_fk = :id ORDER BY id_apertura_caja_fk DESC" )
    fun leerPorId(id: Int): Flow<DetalleAperturaCajaEntity>

    @Query( "SELECT * FROM tab_detalles_aperturas_caja WHERE id_apertura_caja_fk = :id ORDER BY id_apertura_caja_fk DESC" )
    fun leerPorId(id: Float): Flow<DetalleAperturaCajaEntity>

    @Query( "SELECT * FROM tab_detalles_aperturas_caja WHERE id_apertura_caja_fk = :id ORDER BY id_apertura_caja_fk DESC" )
    fun leerPorId(id: String): Flow<DetalleAperturaCajaEntity>

    @Query( "DELETE FROM tab_detalles_aperturas_caja" )
    suspend fun borrarTodo()
}