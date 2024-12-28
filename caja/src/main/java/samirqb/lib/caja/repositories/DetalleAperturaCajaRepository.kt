package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IDetalleAperturaCajaDao
import samirqb.lib.caja.entidades.DetalleAperturaCajaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class DetalleAperturaCajaRepository(
    private val mDao: IDetalleAperturaCajaDao
):IBaseRepository<DetalleAperturaCajaEntity> {

    override suspend fun insertar(mTEntity: DetalleAperturaCajaEntity) {
        mDao.insertar(mTEntity)
    }

    suspend fun insertarVarios(mArrayTEntity: Array<DetalleAperturaCajaEntity>){
        mDao.insertarVarios(*mArrayTEntity)
    }

    override fun leerPorId(id: Int): Flow<DetalleAperturaCajaEntity> {
        return  mDao.leerPorId(id)
    }

    override fun leerPorId(id: Float): Flow<DetalleAperturaCajaEntity> {
        return mDao.leerPorId(id)
    }

    override fun leerPorId(id: String): Flow<DetalleAperturaCajaEntity> {
        return mDao.leerPorId(id)
    }

    override fun leerTodo(): Flow<List<DetalleAperturaCajaEntity>> {
        return mDao.leerTodo()
    }

    override suspend fun borrarTodo() {
        mDao.borrarTodo()
    }

    override suspend fun borrar(mTEntity: DetalleAperturaCajaEntity) {
        mDao.borrar(mTEntity)
    }

    override suspend fun actualizar(mTEntity: DetalleAperturaCajaEntity) {
        mDao.actualizar(mTEntity)
    }
}