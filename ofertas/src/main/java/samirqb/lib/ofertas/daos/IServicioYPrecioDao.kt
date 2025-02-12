package samirqb.lib.ofertas.daos

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseDao

@Dao
interface IServicioYPrecioDao:IBaseDao<ServicioYPrecioEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY fecha_hora_creacion DESC" )
    fun leerTodo(): Flow<List<ServicioYPrecioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Int): Flow<ServicioYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: Float): Flow<ServicioYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE id_registro = :id" )
    fun leerPorId(id: String): Flow<ServicioYPrecioEntity>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios ORDER BY id_registro DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<ServicioYPrecioEntity?>

    @Query( "DELETE FROM tab_precios_de_servicios" )
    suspend fun borrarTodo()

    //CUSTOM
    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios WHERE precio_activo = :precio_activo" )
    fun leerTodoPorPrecioActivo(precio_activo: Boolean): Flow<List<ServicioYPrecioEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios " +
            "LEFT JOIN tab_servicios " +
            "ON tab_precios_de_servicios.id_servicio_fk = tab_servicios.id_servicio_pk " +
            "WHERE precio_activo = :precio_activo ORDER BY fecha_hora_creacion ASC" )
    fun leerTodoPorPrecioActivoConNombreDelServicio(precio_activo:Boolean): Flow<Map<ServicioYPrecioEntity, @MapColumn(columnName = "descripcion") String>>

    @Transaction
    @Query( "SELECT * FROM tab_precios_de_servicios " +
            "LEFT JOIN tab_servicios " +
            "ON tab_precios_de_servicios.id_servicio_fk = tab_servicios.id_servicio_pk " +
            "ORDER BY fecha_hora_creacion DESC" )
    fun leerTodoConNombreDelServicio(): Flow<Map<ServicioYPrecioEntity, @MapColumn(columnName = "descripcion") String>>
}