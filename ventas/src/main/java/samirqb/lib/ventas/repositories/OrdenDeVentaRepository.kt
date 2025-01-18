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
            throw MyCustomException("Error al insertar OrdenDeVentaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenDeVentaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenDeVentaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<OrdenDeVentaEntity> {
        try {
            return mDao.leerPorId(id)
        } catch (e: Exception) {
            throw MyCustomException("Error al leerPorId OrdenDeVentaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<OrdenDeVentaEntity>> {
        try {
            return mDao.leerTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al leerTodo OrdenDeVentaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception) {
            throw MyCustomException("Error al borrarTodo OrdenDeVentaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: OrdenDeVentaEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al borrar OrdenDeVentaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: OrdenDeVentaEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception) {
            throw MyCustomException("Error al actualizar OrdenDeVentaEntity", e)
        }
    }
}
