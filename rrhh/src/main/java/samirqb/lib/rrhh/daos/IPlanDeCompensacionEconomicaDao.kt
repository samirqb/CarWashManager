package samirqb.lib.rrhh.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.entities.PlanDeCompensacionEconomicaEntity
import samirqb.lib.rrhh.interfaces.IBaseDao

@Dao
interface IPlanDeCompensacionEconomicaDao: IBaseDao<PlanDeCompensacionEconomicaEntity> {

    @Transaction
    @Query("SELECT * FROM tab_planes_de_compensacion_economica ORDER BY id_plan_compensacion_pk ASC")
    fun leerTodo(): Flow<List<PlanDeCompensacionEconomicaEntity>>

    @Transaction
    @Query("SELECT * FROM tab_planes_de_compensacion_economica WHERE id_plan_compensacion_pk = :id")
    fun leerPorId(id: Int): Flow<PlanDeCompensacionEconomicaEntity>

    @Transaction
    @Query("SELECT * FROM tab_planes_de_compensacion_economica WHERE id_plan_compensacion_pk = :id")
    fun leerPorId(id: Float): Flow<PlanDeCompensacionEconomicaEntity>

    @Transaction
    @Query("SELECT * FROM tab_planes_de_compensacion_economica WHERE id_plan_compensacion_pk = :id")
    fun leerPorId(id: String): Flow<PlanDeCompensacionEconomicaEntity>

    @Transaction
    @Query("SELECT * FROM tab_planes_de_compensacion_economica ORDER BY id_plan_compensacion_pk DESC LIMIT 1 ")
    fun leerMasReciente(): Flow<PlanDeCompensacionEconomicaEntity?>

    @Query( "DELETE FROM tab_planes_de_compensacion_economica" )
    suspend fun borrarTodo()

}