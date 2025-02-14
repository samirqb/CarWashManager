package samirqb.lib.pagos.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.MyCustomException
import samirqb.lib.pagos.daos.IDatosTotalesOrdenPagoNominaDao
import samirqb.lib.pagos.entities.DatosTotalesOrdenPagoNominaEntity
import samirqb.lib.pagos.interfaces.IBaseRepository

class DatosTotalesOrdenPagoNominaRepository(
    private val mDao: IDatosTotalesOrdenPagoNominaDao
):IBaseRepository<DatosTotalesOrdenPagoNominaEntity> {

    override suspend fun insertar(mTEntity: DatosTotalesOrdenPagoNominaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override fun leerPorId(id: Int): Flow<DatosTotalesOrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override fun leerPorId(id: Float): Flow<DatosTotalesOrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override fun leerPorId(id: String): Flow<DatosTotalesOrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override fun leerTodo(): Flow<List<DatosTotalesOrdenPagoNominaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override suspend fun borrar(mTEntity: DatosTotalesOrdenPagoNominaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }

    override suspend fun actualizar(mTEntity: DatosTotalesOrdenPagoNominaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar en DatosTotalesOrdenPagoNominaRepository", e)
        }
    }
    
}