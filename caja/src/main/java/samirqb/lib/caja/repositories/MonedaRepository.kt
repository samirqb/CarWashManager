package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.customexception.MyCustomException
import samirqb.lib.caja.daos.IMonedaDao
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class MonedaRepository(
    val mMonedaDao: IMonedaDao
):IBaseRepository<MonedaEntity> {

    override suspend fun insertar(mTEntity: MonedaEntity) {
        try {
            mMonedaDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar MonedaEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<MonedaEntity> {
        return try {
            mMonedaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId MonedaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<MonedaEntity> {
        return try {
            mMonedaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId MonedaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<MonedaEntity> {
        return try {
            mMonedaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId MonedaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<MonedaEntity>> {
        return try {
            mMonedaDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo MonedaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mMonedaDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo MonedaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: MonedaEntity) {
        try {
            mMonedaDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar MonedaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: MonedaEntity) {
        try {
            mMonedaDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar MonedaEntity", e)
        }
    }

}