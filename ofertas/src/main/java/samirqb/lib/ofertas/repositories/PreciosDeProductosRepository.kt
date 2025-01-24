package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException

import samirqb.lib.ofertas.daos.IPrecioDeProductoDao
import samirqb.lib.ofertas.entities.PrecioDeProductoEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class PreciosDeProductosRepository(
    val mDao: IPrecioDeProductoDao
): IBaseRepository<PrecioDeProductoEntity> {

    override suspend fun insertar(mTEntity: PrecioDeProductoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PrecioDeProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PrecioDeProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PrecioDeProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PrecioDeProductoEntity>> {
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

    override suspend fun borrar(mTEntity: PrecioDeProductoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeProductosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PrecioDeProductoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeProductosEntity", e)
        }
    }

}