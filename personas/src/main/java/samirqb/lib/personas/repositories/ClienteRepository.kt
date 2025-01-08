package samirqb.lib.personas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.CustomDatabaseException
import samirqb.lib.personas.daos.IClienteDao
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.interfaces.IBaseRepository

class ClienteRepository(
    val mDao: IClienteDao
):IBaseRepository<ClienteEntity> {
    override suspend fun insertar(mTEntity: ClienteEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al insertar ClienteEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ClienteEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClienteEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ClienteEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClienteEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ClienteEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClienteEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ClienteEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerTodo ClienteEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrarTodo ClienteEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ClienteEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrar ClienteEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ClienteEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al actualizar ClienteEntity", e)
        }
    }
}
