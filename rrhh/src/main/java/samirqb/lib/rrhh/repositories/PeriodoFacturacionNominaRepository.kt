package samirqb.lib.rrhh.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.MyCustomException
import samirqb.lib.rrhh.daos.IPeriodoFacturacionNominaDao
import samirqb.lib.rrhh.entities.PeriodoFacturacionNominaEntity
import samirqb.lib.rrhh.interfaces.IBaseRepository

class PeriodoFacturacionNominaRepository(
    val mDao: IPeriodoFacturacionNominaDao
): IBaseRepository<PeriodoFacturacionNominaEntity> {
    override suspend fun insertar(mTEntity: PeriodoFacturacionNominaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar PeriodoFacturacionNominaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PeriodoFacturacionNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PeriodoFacturacionNominaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PeriodoFacturacionNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PeriodoFacturacionNominaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PeriodoFacturacionNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PeriodoFacturacionNominaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PeriodoFacturacionNominaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo PeriodoFacturacionNominaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo PeriodoFacturacionNominaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: PeriodoFacturacionNominaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar PeriodoFacturacionNominaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PeriodoFacturacionNominaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar PeriodoFacturacionNominaEntity", e)
        }
    }
}
