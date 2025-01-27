package samirqb.lib.ofertas.repositories

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
            throw MyCustomException("Error al insertar PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ServicioYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ServicioYPrecioEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo PreciosDeServiciosEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo PreciosDeServiciosEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ServicioYPrecioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeServiciosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ServicioYPrecioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeServiciosEntity", e)
        }
    }

}