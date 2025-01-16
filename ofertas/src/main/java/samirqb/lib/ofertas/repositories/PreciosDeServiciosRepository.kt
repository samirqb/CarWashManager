package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IPreciosDeServiciosDao
import samirqb.lib.ofertas.entities.PreciosDeServiciosEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class PreciosDeServiciosRepository(
    val mDao: IPreciosDeServiciosDao
): IBaseRepository<PreciosDeServiciosEntity> {

    override suspend fun insertar(mTEntity: PreciosDeServiciosEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PreciosDeServiciosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PreciosDeServiciosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PreciosDeServiciosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeServiciosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PreciosDeServiciosEntity>> {
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

    override suspend fun borrar(mTEntity: PreciosDeServiciosEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeServiciosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PreciosDeServiciosEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeServiciosEntity", e)
        }
    }

}