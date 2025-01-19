package samirqb.lib.rrhh.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.MyCustomException
import samirqb.lib.rrhh.daos.IOperarioYContratoDao
import samirqb.lib.rrhh.entities.OperarioYContratoEntity
import samirqb.lib.rrhh.interfaces.IBaseRepository

class OperarioYContratoRepository(
    val mDao: IOperarioYContratoDao
): IBaseRepository<OperarioYContratoEntity> {
    override suspend fun insertar(mTEntity: OperarioYContratoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar OperarioYContratoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OperarioYContratoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OperarioYContratoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OperarioYContratoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OperarioYContratoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<OperarioYContratoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OperarioYContratoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<OperarioYContratoEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo OperarioYContratoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo OperarioYContratoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: OperarioYContratoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar OperarioYContratoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: OperarioYContratoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar OperarioYContratoEntity", e)
        }
    }
}
