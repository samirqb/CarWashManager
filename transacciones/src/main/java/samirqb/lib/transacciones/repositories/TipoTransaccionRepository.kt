package samirqb.lib.transacciones.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.MyCustomException
import samirqb.lib.transacciones.daos.ITipoTransaccionDao
import samirqb.lib.transacciones.entities.TipoTransaccionEntity
import samirqb.lib.transacciones.interfaces.IBaseRepository

class TipoTransaccionRepository(
    val mDao: ITipoTransaccionDao
): IBaseRepository<TipoTransaccionEntity> {
    override suspend fun insertar(mTEntity: TipoTransaccionEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar TipoTransaccionEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<TipoTransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TipoTransaccionEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<TipoTransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TipoTransaccionEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<TipoTransaccionEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId TipoTransaccionEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<TipoTransaccionEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo TipoTransaccionEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo TipoTransaccionEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: TipoTransaccionEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar TipoTransaccionEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: TipoTransaccionEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar TipoTransaccionEntity", e)
        }
    }
}
