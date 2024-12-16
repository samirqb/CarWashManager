package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.ITipoMonedaDao
import samirqb.lib.caja.entidades.TipoMonedaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class TipoMonedaRepository(private val  dao: ITipoMonedaDao):IBaseRepository<TipoMonedaEntity> {

    override suspend fun insertar(mTEntity: TipoMonedaEntity) {
        dao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<TipoMonedaEntity> {
        TODO("Not yet implemented")
    }

    override fun leerPorId(id: Float): Flow<TipoMonedaEntity> {
        TODO("Not yet implemented")
    }

    override fun leerPorId(id: String): Flow<TipoMonedaEntity> {
        TODO("Not yet implemented")
    }

    override fun leerTodo(): Flow<List<TipoMonedaEntity>> {
        return dao.leerTodo()
    }

    override suspend fun borrarTodo() {
        dao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: TipoMonedaEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun actualizar(mTEntity: TipoMonedaEntity) {
        TODO("Not yet implemented")
    }
}