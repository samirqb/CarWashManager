package samirqb.lib.personas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.personas.daos.IClienteDao
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.interfaces.IBaseRepository

class ClienteRepository(
    val mDao: IClienteDao
):IBaseRepository<ClienteEntity> {
    override suspend fun insertar(mTEntity: ClienteEntity) {
        mDao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<ClienteEntity> {
        return leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<ClienteEntity> {
        return leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<ClienteEntity> {
        return leerPorId(id)
    }

    override fun leerTodo(): Flow<List<ClienteEntity>> {
        return leerTodo()
    }

    override suspend fun borrarTodo() {
        mDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: ClienteEntity) {
        mDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: ClienteEntity) {
        mDao.actualizar(mTEntity)
    }
}
