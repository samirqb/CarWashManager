package samirqb.lib.rrhh.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.MyCustomException
import samirqb.lib.rrhh.daos.IAcuerdoDePagoNominaDao
import samirqb.lib.rrhh.entities.AcuerdoDePagoNominaEntity
import samirqb.lib.rrhh.interfaces.IBaseRepository


class AcuerdoDePagoNominaRepository(
    val mDao: IAcuerdoDePagoNominaDao
): IBaseRepository<AcuerdoDePagoNominaEntity> {
    override suspend fun insertar(mTEntity: AcuerdoDePagoNominaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar AcuerdoDePagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<AcuerdoDePagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId AcuerdoDePagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<AcuerdoDePagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId AcuerdoDePagoNominaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<AcuerdoDePagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId AcuerdoDePagoNominaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<AcuerdoDePagoNominaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo AcuerdoDePagoNominaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo AcuerdoDePagoNominaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: AcuerdoDePagoNominaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar AcuerdoDePagoNominaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: AcuerdoDePagoNominaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar AcuerdoDePagoNominaEntity", e)
        }
    }
}
