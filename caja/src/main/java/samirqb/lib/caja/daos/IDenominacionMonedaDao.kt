package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.DenominacionMonedaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IDenominacionMonedaDao: IBaseDao<DenominacionMonedaEntity> {

    @Query( "SELECT * FROM tab_denominaciones_de_moneda ORDER BY denominacion_pk ASC" )
    fun leerTodo(): Flow<List<DenominacionMonedaEntity>>

    @Query( "SELECT * FROM tab_denominaciones_de_moneda WHERE denominacion_pk = :id ORDER BY denominacion_pk ASC" )
    fun leerPorId(id: Int): Flow<DenominacionMonedaEntity>

    @Query( "SELECT * FROM tab_denominaciones_de_moneda WHERE denominacion_pk = :id ORDER BY denominacion_pk ASC" )
    fun leerPorId(id: Float): Flow<DenominacionMonedaEntity>

    @Query( "SELECT * FROM tab_denominaciones_de_moneda WHERE denominacion_pk = :id ORDER BY denominacion_pk ASC" )
    fun leerPorId(id: String): Flow<DenominacionMonedaEntity>

    @Query( "DELETE FROM tab_denominaciones_de_moneda" )
    suspend fun borrarTodo()

}