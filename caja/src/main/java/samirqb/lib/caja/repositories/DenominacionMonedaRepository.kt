package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IDenominacionMonedaDao
import samirqb.lib.caja.entidades.DenominacionMonedaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class DenominacionMonedaRepository(
    val mIDenominacionMonedaDao: IDenominacionMonedaDao
):IBaseRepository<DenominacionMonedaEntity> {

    override suspend fun insertar(mTEntity: DenominacionMonedaEntity) {
        mIDenominacionMonedaDao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<DenominacionMonedaEntity> {
        return mIDenominacionMonedaDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<DenominacionMonedaEntity> {
        return mIDenominacionMonedaDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<DenominacionMonedaEntity> {
        return mIDenominacionMonedaDao.leerPorId(id)
    }

    override fun leerTodo(): Flow<List<DenominacionMonedaEntity>> {
        return mIDenominacionMonedaDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mIDenominacionMonedaDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: DenominacionMonedaEntity) {
        mIDenominacionMonedaDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: DenominacionMonedaEntity) {
        mIDenominacionMonedaDao.actualizar(mTEntity)
    }

}