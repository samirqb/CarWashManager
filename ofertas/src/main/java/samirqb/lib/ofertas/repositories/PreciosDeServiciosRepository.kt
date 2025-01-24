package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IPrecioDeServicioDao
import samirqb.lib.ofertas.entities.PrecioDeServicioEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class PreciosDeServiciosRepository(
    val mDao: IPrecioDeServicioDao
): IBaseRepository<PrecioDeServicioEntity> {

    override suspend fun insertar(mTEntity: PrecioDeServicioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PrecioDeServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PrecioDeServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PrecioDeServicioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PrecioDeServicioEntity>> {
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

    override suspend fun borrar(mTEntity: PrecioDeServicioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeServiciosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PrecioDeServicioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeServiciosEntity", e)
        }
    }

}