package samirqb.lib.rrhh.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.entities.PeriodoFacturacionNominaEntity
import samirqb.lib.rrhh.interfaces.IBaseDao

@Dao
interface IPeriodoFacturacionNominaDao: IBaseDao<PeriodoFacturacionNominaEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_periodos_de_facturacion_nomina ORDER BY id_periodo_facturacion_pk ASC" )
    fun leerTodo(): Flow<List<PeriodoFacturacionNominaEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_periodos_de_facturacion_nomina WHERE id_periodo_facturacion_pk = :id" )
    fun leerPorId(id: Int): Flow<PeriodoFacturacionNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_periodos_de_facturacion_nomina WHERE id_periodo_facturacion_pk = :id" )
    fun leerPorId(id: Float): Flow<PeriodoFacturacionNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_periodos_de_facturacion_nomina WHERE id_periodo_facturacion_pk = :id" )
    fun leerPorId(id: String): Flow<PeriodoFacturacionNominaEntity>

    @Transaction
    @Query( "SELECT * FROM tab_periodos_de_facturacion_nomina ORDER BY id_periodo_facturacion_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<PeriodoFacturacionNominaEntity?>

    @Query( "DELETE FROM tab_periodos_de_facturacion_nomina" )
    suspend fun borrarTodo()
    
}