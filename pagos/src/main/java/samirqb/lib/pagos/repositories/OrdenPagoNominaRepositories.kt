package samirqb.lib.pagos.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.MyCustomException
import samirqb.lib.pagos.daos.IOrdenPagoNominaDao
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.interfaces.IBaseRepository

class OrdenPagoNominaRepositories(
    private val mDao: IOrdenPagoNominaDao
):IBaseRepository<OrdenPagoNominaEntity> {
    override suspend fun insertar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar OrdenPagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenPagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenPagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenPagoNominaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<OrdenPagoNominaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo OrdenPagoNominaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo OrdenPagoNominaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar OrdenPagoNominaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar OrdenPagoNominaEntity", e)
        }
    }
}