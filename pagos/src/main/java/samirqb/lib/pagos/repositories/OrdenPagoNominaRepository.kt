package samirqb.lib.pagos.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.pagos.MyCustomException
import samirqb.lib.pagos.daos.IOrdenPagoNominaDao
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity
import samirqb.lib.pagos.interfaces.IBaseRepository

class OrdenPagoNominaRepository(
    private val mDao: IOrdenPagoNominaDao
):IBaseRepository<OrdenPagoNominaEntity> {
    override suspend fun insertar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar en OrdenPagoNominaRepositories", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenPagoNominaRepositories", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenPagoNominaRepositories", e)
        }
    }

    override fun leerPorId(id: String): Flow<OrdenPagoNominaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenPagoNominaRepositories", e)
        }
    }

    override fun leerTodo(): Flow<List<OrdenPagoNominaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo en OrdenPagoNominaRepositories", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo en OrdenPagoNominaRepositories", e)
        }
    }

    override suspend fun borrar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar en OrdenPagoNominaRepositories", e)
        }
    }

    override suspend fun actualizar(mTEntity: OrdenPagoNominaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar en OrdenPagoNominaRepositories", e)
        }
    }

    //CUSTOM
    fun leerTodoPorPagar(orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        try {
            return mDao.leerPorOrdenPagada(orden_vigente)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodoPorPagar en OrdenPagoNominaRepositories", e)
        }
    }

    fun leerPorOperarioIdYOrdenVigente(operario_id: String, orden_vigente: Boolean): Flow<List<OrdenPagoNominaEntity>> {
        return try {
            return mDao.leerPorOperarioIdYOrdenVigente(operario_id ,orden_vigente)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorOperarioIdYOrdenPagada en OrdenPagoNominaRepositories", e)
        }
    }

    fun leerMasReciente(): Flow<OrdenPagoNominaEntity?>{
        return try {
            mDao.leerMasReciente()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerMasReciente en OrdenPagoNominaRepositories", e)
        }
    }


    fun obtenerCantidadDeRegistros(): Flow<Int>{
        return try {
            mDao.obtenerCantidadDeRegistros()
        } catch (e: Exception) {
            throw MyCustomException("Error al obtenerCantidadDeRegistros en OrdenPagoNominaRepositories", e)
        }
    }
}