package samirqb.lib.ofertas.repositories

import androidx.room.MapColumn
import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IServicioYPrecioDao
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class ServiciosYPreciosRepository(
    val mDao: IServicioYPrecioDao
): IBaseRepository<ServicioYPrecioEntity> {

    override suspend fun insertar(mTEntity: ServicioYPrecioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar en ServiciosYPreciosRepository", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId en ServiciosYPreciosRepository", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId en ServiciosYPreciosRepository", e)
        }
    }

    override fun leerPorId(id: String): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId en ServiciosYPreciosRepository", e)
        }
    }

    override fun leerTodo(): Flow<List<ServicioYPrecioEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo en ServiciosYPreciosRepository", e)
        }
    }
    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo en ServiciosYPreciosRepository", e)
        }
    }

    override suspend fun borrar(mTEntity: ServicioYPrecioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar en ServiciosYPreciosRepository", e)
        }
    }

    override suspend fun actualizar(mTEntity: ServicioYPrecioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar en ServiciosYPreciosRepository", e)
        }
    }

    //CUSTOM
    fun leerTodoPorPrecioActivo(precio_activo: Boolean): Flow<List<ServicioYPrecioEntity>>{
        return try {
            mDao.leerTodoPorPrecioActivo( precio_activo )
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodoPorPrecioActivo en ServiciosYPreciosRepository", e)
        }
    }

    fun leerTodoPorPrecioActivoConNombreDelServicio(precio_activo: Boolean ): Flow<Map<ServicioYPrecioEntity, String>>{
        return try {
            mDao.leerTodoPorPrecioActivoConNombreDelServicio( precio_activo )
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodoPorPrecioActivoConNombreDelServicio en ServiciosYPreciosRepository", e)
        }
    }

    fun leerTodoConNombreDelServicio(): Flow<Map<ServicioYPrecioEntity, String>> {
        return try {
            mDao.leerTodoConNombreDelServicio()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodoConNombreDelServicio en ServiciosYPreciosRepository", e)
        }
    }
}
