package samirqb.lib.ventas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.customexceptions.MyCustomException
import samirqb.lib.ventas.daos.IDetalleOrdenProductoDao
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.interfaces.IBaseRepository

class DetalleOrdenProductoRepository(
    private val mDao: IDetalleOrdenProductoDao
): IBaseRepository<DetalleOrdenProductoEntity> {
    override suspend fun insertar(mTEntity: DetalleOrdenProductoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar DetalleOrdenProductoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<DetalleOrdenProductoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenProductoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<DetalleOrdenProductoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenProductoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<DetalleOrdenProductoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenProductoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<DetalleOrdenProductoEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo DetalleOrdenProductoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo DetalleOrdenProductoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: DetalleOrdenProductoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar DetalleOrdenProductoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: DetalleOrdenProductoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar DetalleOrdenProductoEntity", e)
        }
    }
}
