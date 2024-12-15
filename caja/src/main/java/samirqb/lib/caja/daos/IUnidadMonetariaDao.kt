package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.entidades.UnidadMonetariaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IUnidadMonetariaDao: IBaseDao<UnidadMonetariaEntity> {

    @Query( "SELECT * FROM tab_unidades_monetarias ORDER BY codigo_iso_4217_pk ASC" )
    fun leerTodo(): Flow<List<UnidadMonetariaEntity>>

    @Query( "DELETE FROM tab_unidades_monetarias" )
    suspend fun borrarTodo()

}