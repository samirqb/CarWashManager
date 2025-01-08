package samirqb.lib.vehiculos.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.vehiculos.CustomDatabaseException
import samirqb.lib.vehiculos.dao.IClasificacionDelVehiculoDao
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.interfaces.IBaseRepository

class ClasificacionDelVehiculoRepository(
    private val mDao: IClasificacionDelVehiculoDao
): IBaseRepository<ClasificacionDelVehiculoEntity> {
    override suspend fun insertar(mTEntity: ClasificacionDelVehiculoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al insertar ClasificacionDelVehiculoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ClasificacionDelVehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClasificacionDelVehiculoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ClasificacionDelVehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClasificacionDelVehiculoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ClasificacionDelVehiculoEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerPorId ClasificacionDelVehiculoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ClasificacionDelVehiculoEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al leerTodo ClasificacionDelVehiculoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrarTodo ClasificacionDelVehiculoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ClasificacionDelVehiculoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al borrar ClasificacionDelVehiculoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ClasificacionDelVehiculoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw CustomDatabaseException("Error al actualizar ClasificacionDelVehiculoEntity", e)
        }
    }
}