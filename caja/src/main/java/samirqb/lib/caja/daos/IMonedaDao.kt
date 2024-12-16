package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IMonedaDao:IBaseDao<MonedaEntity> {

    @Query( "SELECT * FROM tab_monedas ORDER BY denominacion_fk ASC" )
    fun leerTodo(): Flow<List<MonedaEntity>>

    @Query( "SELECT * FROM tab_monedas WHERE id_moneda_pk = :id ORDER BY denominacion_fk ASC" )
    fun leerPorId(id: Int): Flow<MonedaEntity>

    @Query( "SELECT * FROM tab_monedas WHERE id_moneda_pk = :id ORDER BY denominacion_fk ASC" )
    fun leerPorId(id: Float): Flow<MonedaEntity>

    @Query( "SELECT * FROM tab_monedas WHERE id_moneda_pk = :id ORDER BY denominacion_fk ASC" )
    fun leerPorId(id: String): Flow<MonedaEntity>

    @Query( "DELETE FROM tab_monedas" )
    suspend fun borrarTodo()

}