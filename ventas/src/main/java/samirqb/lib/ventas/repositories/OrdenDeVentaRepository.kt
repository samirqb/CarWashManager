package samirqb.lib.ventas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ventas.customexceptions.MyCustomException
import samirqb.lib.ventas.daos.IOrdenDeVentaDao
import samirqb.lib.ventas.entities.OrdenDeVentaEntity
import samirqb.lib.ventas.interfaces.IBaseRepository

class OrdenDeVentaRepository(
    private val mDao: IOrdenDeVentaDao
): IBaseRepository<OrdenDeVentaEntity> {
    override suspend fun insertar(mTEntity: OrdenDeVentaEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al insertar en OrdenDeVentaRepository", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenDeVentaRepository", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenDeVentaRepository", e)
        }
    }

    override fun leerPorId(id: String): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId en OrdenDeVentaRepository", e)
        }
    }

    override fun leerTodo(): Flow<List<OrdenDeVentaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo en OrdenDeVentaRepository", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo en OrdenDeVentaRepository", e)
        }
    }

    override suspend fun borrar(mTEntity: OrdenDeVentaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar en OrdenDeVentaRepository", e)
        }
    }

    override suspend fun actualizar(mTEntity: OrdenDeVentaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar en OrdenDeVentaRepository", e)
        }
    }

    //CUSTOM
    fun leerPorVigencia(orden_vigente: Boolean): Flow<List<OrdenDeVentaEntity>> {
        return try {
            mDao.leerPorVigencia(orden_vigente)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorVigencia en OrdenDeVentaRepository", e)
        }
    }

    fun leerMasReciente(): Flow<OrdenDeVentaEntity?>{
        return try {
            mDao.leerMasReciente()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerMasReciente en OrdenDeVentaRepository", e)
        }
    }

    fun obtenerCantidadDeRegistros(): Flow<Int>{
        return try {
            mDao.obtenerCantidadDeRegistros()
        } catch (e: Exception) {
            throw MyCustomException("Error al obtenerCantidadDeRegistros en OrdenDeVentaRepository", e)
        }
    }
}
