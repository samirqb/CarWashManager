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
            throw MyCustomException("Error al insertar en DetalleOrdenServicioRepository", e)
        }
    }

    override fun leerPorId(id: Int): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DetalleOrdenServicioRepository", e)
        }
    }

    override fun leerPorId(id: Float): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DetalleOrdenServicioRepository", e)
        }
    }

    override fun leerPorId(id: String): Flow<DetalleOrdenServicioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DetalleOrdenServicioRepository", e)
        }
    }

    override fun leerTodo(): Flow<List<DetalleOrdenServicioEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo en DetalleOrdenServicioRepository", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo en DetalleOrdenServicioRepository", e)
        }
    }

    override suspend fun borrar(mTEntity: DetalleOrdenServicioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar en DetalleOrdenServicioRepository", e)
        }
    }

    override suspend fun actualizar(mTEntity: DetalleOrdenServicioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar en DetalleOrdenServicioRepository", e)
        }
    }

    //CUSTOM
    suspend fun insertarVarios( mEntities: List<DetalleOrdenServicioEntity>){
        try {
            mDao.insertarVarios(mEntities)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertarVarios en DetalleOrdenServicioRepository", e)
        }
    }

    fun leerPorIdDeOrden(id: Int): Flow<List<DetalleOrdenServicioEntity>>{
        return try {
            mDao.leerPorIdDeOrden(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorIdDeOrden en DetalleOrdenServicioRepository", e)
        }
    }
}