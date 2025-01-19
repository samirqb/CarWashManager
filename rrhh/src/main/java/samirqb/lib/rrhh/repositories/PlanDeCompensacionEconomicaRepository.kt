package samirqb.lib.rrhh.repositories


import kotlinx.coroutines.flow.Flow
import samirqb.lib.rrhh.MyCustomException
import samirqb.lib.rrhh.daos.IPlanDeCompensacionEconomicaDao
import samirqb.lib.rrhh.entities.PlanDeCompensacionEconomicaEntity
import samirqb.lib.rrhh.interfaces.IBaseRepository

class PlanDeCompensacionEconomicaRepositoryMyCustomException(
    val mDao: IPlanDeCompensacionEconomicaDao
): IBaseRepository<PlanDeCompensacionEconomicaEntity> {
    override suspend fun insertar(mTEntity: PlanDeCompensacionEconomicaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<PlanDeCompensacionEconomicaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<PlanDeCompensacionEconomicaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<PlanDeCompensacionEconomicaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<PlanDeCompensacionEconomicaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: PlanDeCompensacionEconomicaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar PlanDeCompensacionEconomicaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: PlanDeCompensacionEconomicaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar PlanDeCompensacionEconomicaEntity", e)
        }
    }
}
