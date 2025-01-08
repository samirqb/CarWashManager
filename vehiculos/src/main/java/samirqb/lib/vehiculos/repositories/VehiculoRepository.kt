package samirqb.lib.vehiculos.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.CustomDatabaseException
import samirqb.lib.vehiculos.dao.IVehiculoDao
import samirqb.lib.vehiculos.entities.VehiculoEntity
import samirqb.lib.vehiculos.interfaces.IBaseRepository

class VehiculoRepository(
    private val mDao: IVehiculoDao
): IBaseRepository<VehiculoEntity> {
    override suspend fun insertar(mTEntity: VehiculoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al insertar VehiculoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<VehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId VehiculoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<VehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId VehiculoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<VehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId VehiculoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<VehiculoEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerTodo VehiculoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrarTodo VehiculoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: VehiculoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrar VehiculoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: VehiculoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al actualizar VehiculoEntity", e)
        }
    }
}
