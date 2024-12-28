package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IDetalleCierreCajaDao
import samirqb.lib.caja.entidades.DetalleCierreCajaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class DetalleCierreCajaRepository(
    private val mDao: IDetalleCierreCajaDao
): IBaseRepository<DetalleCierreCajaEntity> {

    override suspend fun insertar(mTEntity: DetalleCierreCajaEntity) {
        mDao.insertar(mTEntity)
    }

    suspend fun insertarVarios(mArrayTEntity: Array<DetalleCierreCajaEntity>){
        mDao.insertarVarios(*mArrayTEntity)
    }

    override fun leerPorId(id: Int): Flow<DetalleCierreCajaEntity> {
        return  mDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<DetalleCierreCajaEntity> {
        return mDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<DetalleCierreCajaEntity> {
        return mDao.leerPorId(id)
    }

    override fun leerTodo(): Flow<List<DetalleCierreCajaEntity>> {
        return mDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: DetalleCierreCajaEntity) {
        mDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: DetalleCierreCajaEntity) {
        mDao.actualizar(mTEntity)
    }
}