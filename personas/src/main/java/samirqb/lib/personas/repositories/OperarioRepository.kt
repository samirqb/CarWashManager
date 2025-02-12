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
            throw CustomDatabaseException("Error al insertar en OperarioRepository", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId en OperarioRepository", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId en OperarioRepository", e)
        }
    }

    override fun leerPorId(id: String): Flow<OperarioEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId en OperarioRepository", e)
        }
    }

    override fun leerTodo(): Flow<List<OperarioEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerTodo en OperarioRepository", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrarTodo en OperarioRepository", e)
        }
    }

    override suspend fun borrar(mTEntity: OperarioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrar en OperarioRepository", e)
        }
    }

    override suspend fun actualizar(mTEntity: OperarioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al actualizar en OperarioRepository", e)
        }
    }

    //CUSTOM
    fun leerPorOperarioActivo(operario_activo: Boolean): Flow<List<OperarioEntity>>{
        return  try {
            mDao.leerPorOperarioActivo(operario_activo)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorOperarioActivo en OperarioRepository", e)
        }
    }
}
