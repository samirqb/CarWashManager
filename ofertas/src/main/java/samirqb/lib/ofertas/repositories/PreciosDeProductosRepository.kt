package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException

import samirqb.lib.ofertas.daos.IPreciosDeProductosDao
import samirqb.lib.ofertas.entities.PreciosDeProductosEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class PreciosDeProductosRepository(
    val mDao: IPreciosDeProductosDao
): IBaseRepository<PreciosDeProductosEntity> {

    override suspend fun insertar(mTEntity: PreciosDeProductosEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PreciosDeProductosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PreciosDeProductosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PreciosDeProductosEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PreciosDeProductosEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo PreciosDeProductosEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo PreciosDeProductosEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: PreciosDeProductosEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeProductosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PreciosDeProductosEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeProductosEntity", e)
        }
    }

}