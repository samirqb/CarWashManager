package samirqb.lib.vehiculos.dao

import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.interfaces.IBaseDao

interface IVehiculoDao:IBaseDao<VehiculoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_vehiculos ORDER BY matricula_pk ASC" )
    fun leerTodo(): Flow<List<VehiculoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_vehiculos WHERE matricula_pk = :id ORDER BY matricula_pk ASC" )
    fun leerPorId(id: Int): Flow<VehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_vehiculos WHERE matricula_pk = :id ORDER BY matricula_pk ASC" )
    fun leerPorId(id: Float): Flow<VehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_vehiculos WHERE matricula_pk = :id ORDER BY matricula_pk ASC" )
    fun leerPorId(id: String): Flow<VehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_vehiculos ORDER BY matricula_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<VehiculoEntity?>

    @Query( "DELETE FROM tab_vehiculos" )
    suspend fun borrarTodo()

}
