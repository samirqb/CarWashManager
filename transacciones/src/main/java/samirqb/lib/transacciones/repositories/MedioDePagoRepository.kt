package samirqb.lib.transacciones.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.MyCustomException
import samirqb.lib.transacciones.daos.IMedioDePagoDao
import samirqb.lib.transacciones.entities.MedioDePagoEntity
import samirqb.lib.transacciones.interfaces.IBaseRepository

class MedioDePagoRepository(
    val mDao: IMedioDePagoDao
): IBaseRepository<MedioDePagoEntity> {
    override suspend fun insertar(mTEntity: MedioDePagoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar MedioDePagoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<MedioDePagoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId MedioDePagoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<MedioDePagoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId MedioDePagoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<MedioDePagoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId MedioDePagoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<MedioDePagoEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo MedioDePagoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo MedioDePagoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: MedioDePagoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar MedioDePagoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: MedioDePagoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar MedioDePagoEntity", e)
        }
    }
}
