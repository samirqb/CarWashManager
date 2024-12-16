package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IMonedaDao
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class MonedaRepository(
    val mMonedaDao: IMonedaDao
):IBaseRepository<MonedaEntity> {
    override suspend fun insertar(mTEntity: MonedaEntity) {
        mMonedaDao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<MonedaEntity> {
        return mMonedaDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<MonedaEntity> {
        return mMonedaDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<MonedaEntity> {
        return mMonedaDao.leerPorId(id)
    }

    override fun leerTodo(): Flow<List<MonedaEntity>> {
        return mMonedaDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mMonedaDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: MonedaEntity) {
        mMonedaDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: MonedaEntity) {
        mMonedaDao.actualizar(mTEntity)
    }

}