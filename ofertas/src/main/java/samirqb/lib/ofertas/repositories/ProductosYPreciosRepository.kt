package samirqb.lib.ofertas.repositories

import kotlinx.coroutines.flow.Flow
import samirqb.lib.ofertas.customexception.MyCustomException

import samirqb.lib.ofertas.daos.IProductoYPrecioDao
import samirqb.lib.ofertas.entities.ProductoYPrecioEntity
import samirqb.lib.ofertas.interfaces.IBaseRepository

class ProductosYPreciosRepository(
    val mDao: IProductoYPrecioDao
): IBaseRepository<ProductoYPrecioEntity> {

    override suspend fun insertar(mTEntity: ProductoYPrecioEntity) {
        try {
            mDao.insertar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al insertar PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Int): Flow<ProductoYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: Float): Flow<ProductoYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerPorId(id: String): Flow<ProductoYPrecioEntity> {
        return try {
            mDao.leerPorId(id)
        } catch (e: Exception){
            throw MyCustomException("Error al leerPorId PreciosDeProductosEntity", e)
        }
    }

    override fun leerTodo(): Flow<List<ProductoYPrecioEntity>> {
        return try {
            mDao.leerTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al leerTodo PreciosDeProductosEntity", e)
        }
    }

    override suspend fun borrarTodo() {
        try {
            mDao.borrarTodo()
        } catch (e: Exception){
            throw MyCustomException("Error al borrarTodo PreciosDeProductosEntity", e)
        }
    }

    override suspend fun borrar(mTEntity: ProductoYPrecioEntity) {
        try {
            mDao.borrar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al borrar PreciosDeProductosEntity", e)
        }
    }

    override suspend fun actualizar(mTEntity: ProductoYPrecioEntity) {
        try {
            mDao.actualizar(mTEntity)
        } catch (e: Exception){
            throw MyCustomException("Error al actualizar PreciosDeProductosEntity", e)
        }
    }

}