package samirqb.lib.ofertas.interfaces

import kotlinx.coroutines.flow.Flow

interface IBaseRepository<TEntity>{

    suspend fun insertar( mTEntity: TEntity)

    fun leerPorId( id: Int): Flow<TEntity>

    fun leerPorId( id: Float): Flow<TEntity>

    fun leerPorId( id: String): Flow<TEntity>

    fun leerTodo(): Flow<List<TEntity>>

    suspend fun actualizar( mTEntity: TEntity)

    suspend fun borrar( mTEntity: TEntity)

    suspend fun borrarTodo()

}