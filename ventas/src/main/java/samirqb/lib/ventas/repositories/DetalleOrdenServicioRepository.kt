package samirqb.lib.ventas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.customexceptions.MyCustomException
import samirqb.lib.ventas.daos.IDetalleOrdenServicioDao
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.interfaces.IBaseRepository

class DetalleOrdenServicioRepository(
    private val mDao: IDetalleOrdenServicioDao
): IBaseRepository<DetalleOrdenServicioEntity> {
    override suspend fun insertar(mTEntity: DetalleOrdenServicioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar DetalleOrdenServicioEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenServicioEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenServicioEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId DetalleOrdenServicioEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<DetalleOrdenServicioEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo DetalleOrdenServicioEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo DetalleOrdenServicioEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: DetalleOrdenServicioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar DetalleOrdenServicioEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: DetalleOrdenServicioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar DetalleOrdenServicioEntity", e)
        }
    }
}