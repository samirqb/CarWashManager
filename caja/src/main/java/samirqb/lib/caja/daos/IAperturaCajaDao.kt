package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IAperturaCajaDao:IBaseDao<AperturaCajaEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_aperturas_caja ORDER BY id_apertura_caja_pk ASC" )
    fun leerTodo(): Flow<List<AperturaCajaEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_aperturas_caja WHERE id_apertura_caja_pk = :id ORDER BY id_apertura_caja_pk ASC" )
    fun leerPorId(id: Int): Flow<AperturaCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_aperturas_caja WHERE id_apertura_caja_pk = :id ORDER BY id_apertura_caja_pk ASC" )
    fun leerPorId(id: Float): Flow<AperturaCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_aperturas_caja WHERE id_apertura_caja_pk = :id ORDER BY id_apertura_caja_pk ASC" )
    fun leerPorId(id: String): Flow<AperturaCajaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_aperturas_caja ORDER BY id_apertura_caja_pk DESC LIMIT 1 " )
    fun leerAperturaCajaMasReciente(): Flow<AperturaCajaEntity?>

    @Query( "DELETE FROM tab_aperturas_caja" )
    suspend fun borrarTodo()
}
