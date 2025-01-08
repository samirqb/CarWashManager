package samirqb.lib.vehiculos.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.interfaces.IBaseDao

@Dao
interface IClasificacionDelVehiculoDao:IBaseDao<ClasificacionDelVehiculoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_clasificacion_del_vehiculo ORDER BY clase_id_pk ASC" )
    fun leerTodo(): Flow<List<ClasificacionDelVehiculoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_clasificacion_del_vehiculo WHERE clase_id_pk = :id ORDER BY clase_id_pk ASC" )
    fun leerPorId(id: Int): Flow<ClasificacionDelVehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clasificacion_del_vehiculo WHERE clase_id_pk = :id ORDER BY clase_id_pk ASC" )
    fun leerPorId(id: Float): Flow<ClasificacionDelVehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clasificacion_del_vehiculo WHERE clase_id_pk = :id ORDER BY clase_id_pk ASC" )
    fun leerPorId(id: String): Flow<ClasificacionDelVehiculoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_clasificacion_del_vehiculo ORDER BY clase_id_pk DESC LIMIT 1 " )
    fun leerClienteMasReciente(): Flow<ClasificacionDelVehiculoEntity?>

    @Query( "DELETE FROM tab_clasificacion_del_vehiculo" )
    suspend fun borrarTodo()

}
