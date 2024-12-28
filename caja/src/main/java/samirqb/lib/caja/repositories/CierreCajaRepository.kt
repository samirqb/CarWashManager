package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.ICierreCajaDao
import samirqb.lib.caja.entidades.CierreCajaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class CierreCajaRepository(
    val mICierreCajaDao: ICierreCajaDao
):IBaseRepository<CierreCajaEntity> {
    override suspend fun insertar(mTEntity: CierreCajaEntity) {
        mICierreCajaDao.insertar(mTEntity)
    }

    override fun leerPorId(id: Int): Flow<CierreCajaEntity> {
        return mICierreCajaDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<CierreCajaEntity> {
        return mICierreCajaDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<CierreCajaEntity> {
        return mICierreCajaDao.leerPorId(id)
    }

    fun leerCierreCajaMasReciente(): Flow<CierreCajaEntity?>{
        return mICierreCajaDao.leerCierreCajaMasReciente()
    }

    override fun leerTodo(): Flow<List<CierreCajaEntity>> {
        return mICierreCajaDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mICierreCajaDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: CierreCajaEntity) {
        mICierreCajaDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: CierreCajaEntity) {
        mICierreCajaDao.actualizar(mTEntity)
    }
}