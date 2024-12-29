package samirqb.lib.caja.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.customexception.MyCustomException
import samirqb.lib.caja.daos.IAperturaCajaDao
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class AperturaCajaRepository(
    val mIAperturaCajaDao: IAperturaCajaDao
):IBaseRepository<AperturaCajaEntity> {

    override suspend fun insertar(mTEntity: AperturaCajaEntity) {
        try {
            mIAperturaCajaDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar AperturaCajaEntity", e)
        }   
    }

    override fun leerPorId(id: Int): Flow<AperturaCajaEntity> {
        return try {
            mIAperturaCajaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId AperturaCajaEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<AperturaCajaEntity> {
        return try {
            mIAperturaCajaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId AperturaCajaEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<AperturaCajaEntity> {
        return try {
            mIAperturaCajaDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId AperturaCajaEntity", e)
        }
    }

    fun leerAperturaCajaMasReciente(): Flow<AperturaCajaEntity?>{
        return try {
            mIAperturaCajaDao.leerAperturaCajaMasReciente()
        } catch (e: Exception){
            throw MyCustomException("Error al leerAperturaCajaMasReciente AperturaCajaEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<AperturaCajaEntity>> {
        return try {
            mIAperturaCajaDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo AperturaCajaEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mIAperturaCajaDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo AperturaCajaEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: AperturaCajaEntity) {
        try {
            mIAperturaCajaDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar AperturaCajaEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: AperturaCajaEntity) {
        try {
            mIAperturaCajaDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar AperturaCajaEntity", e)
        }
    }
}
