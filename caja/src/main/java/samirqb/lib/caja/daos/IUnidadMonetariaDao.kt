package samirqb.lib.caja.daos

import androidx.room.Dao
import androidx.room.Query
import samirqb.lib.caja.entidades.UnidadMonetariaEntity
import samirqb.lib.caja.interfaces.IBaseDao

@Dao
interface IUnidadMonetariaDao: IBaseDao<UnidadMonetariaEntity> {

    @Query( "DELETE FROM tab_unidades_monetarias" )
    suspend fun deleteAll()

}