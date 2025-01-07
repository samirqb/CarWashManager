package samirqb.lib.personas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.CustomDatabaseException
import samirqb.lib.personas.daos.IOperarioDao
import samirqb.lib.personas.entities.OperarioEntity
import samirqb.lib.personas.interfaces.IBaseRepository

class OperarioRepository(
    val mDao: IOperarioDao
): IBaseRepository<OperarioEntity> {
    override suspend fun insertar(mTEntity: OperarioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al insertar OperarioEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId OperarioEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId OperarioEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId OperarioEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<OperarioEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerTodo OperarioEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrarTodo OperarioEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: OperarioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrar OperarioEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: OperarioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al actualizar OperarioEntity", e)
        }
    }
}
