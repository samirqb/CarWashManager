package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IPrecioDao
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class PrecioRepository(
    val mDao: IPrecioDao
): IBaseRepository<PrecioEntity> {

    override suspend fun insertar(mTEntity: PrecioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PrecioEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PrecioEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PrecioEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PrecioEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PrecioEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo PrecioEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo PrecioEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: PrecioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PrecioEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PrecioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PrecioEntity", e)
        }
    }

    fun leerMasReciente(): Flow<PrecioEntity?> {
        return try {
            mDao.leerMasReciente()
        } catch (e: Exception){
            throw MyCustomException("Error al leerMasReciente PrecioEntity", e)
        }
    }

}