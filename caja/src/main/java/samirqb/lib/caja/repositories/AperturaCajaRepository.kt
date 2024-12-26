package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IAperturaCajaDao
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class AperturaCajaRepository(
    val mIAperturaCajaDao: IAperturaCajaDao
):IBaseRepository<AperturaCajaEntity> {
    override suspend fun insertar(mTEntity: AperturaCajaEntity) {
        mIAperturaCajaDao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<AperturaCajaEntity> {
        return mIAperturaCajaDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<AperturaCajaEntity> {
        return mIAperturaCajaDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<AperturaCajaEntity> {
        return mIAperturaCajaDao.leerPorId(id)
    }

    override fun leerTodo(): Flow<List<AperturaCajaEntity>> {
        return mIAperturaCajaDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mIAperturaCajaDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: AperturaCajaEntity) {
        mIAperturaCajaDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: AperturaCajaEntity) {
        mIAperturaCajaDao.actualizar(mTEntity)
    }
}