package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IServicioDao
import samirqb.lib.ofertas.entities.ServicioEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class ServiciosRepository(
    val mDao:IServicioDao
): IBaseRepository<ServicioEntity> {

    override suspend fun insertar(mTEntity: ServicioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar ServicioEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ServicioEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ServicioEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ServicioEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ServicioEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo ServicioEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo ServicioEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ServicioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar ServicioEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ServicioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar ServicioEntity", e)
        }
    }

}