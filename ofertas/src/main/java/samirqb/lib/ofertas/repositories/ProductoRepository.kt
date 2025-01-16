package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException
import samirqb.lib.ofertas.daos.IProductoDao
import samirqb.lib.ofertas.entities.ProductoEntity

import samirqb.lib.ofertas.interfaces.IBaseRepository

class ProductoRepository(
    val mDao: IProductoDao
): IBaseRepository<ProductoEntity> {

    override suspend fun insertar(mTEntity: ProductoEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar ProductoEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ProductoEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ProductoEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ProductoEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId ProductoEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ProductoEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo ProductoEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo ProductoEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ProductoEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar ProductoEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ProductoEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar ProductoEntity", e)
        }
    }

}