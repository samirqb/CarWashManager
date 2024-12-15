package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.TipoMonedaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface ITipoMonedaDao: IBaseDao<TipoMonedaEntity> {

    @Query( "SELECT * FROM tab_tipos_de_moneda ORDER BY tipo_pk ASC" )
    fun leerTodo(): Flow<List<TipoMonedaEntity>>

    @Query( "DELETE FROM tab_tipos_de_moneda" )
    suspend fun borrarTodo()

}