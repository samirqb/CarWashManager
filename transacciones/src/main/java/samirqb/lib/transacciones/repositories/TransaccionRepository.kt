package samirqb.lib.transacciones.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.MyCustomException
import samirqb.lib.transacciones.daos.ITransaccionDao
import samirqb.lib.transacciones.entities.TransaccionEntity
import samirqb.lib.transacciones.interfaces.IBaseRepository

class TransaccionRepository(
    val mDao: ITransaccionDao
): IBaseRepository<TransaccionEntity> {
    override suspend fun insertar(mTEntity: TransaccionEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar TransaccionEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<TransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TransaccionEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<TransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TransaccionEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<TransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TransaccionEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<TransaccionEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo TransaccionEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo TransaccionEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: TransaccionEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar TransaccionEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: TransaccionEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar TransaccionEntity", e)
        }
    }
}
