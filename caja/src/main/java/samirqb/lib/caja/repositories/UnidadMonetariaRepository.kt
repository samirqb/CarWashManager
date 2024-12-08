package samirqb.lib.caja.repositories

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import samirqb.lib.caja.daos.IUnidadMonetariaDao
import samirqb.lib.caja.entidades.UnidadMonetariaEntity
import samirqb.lib.caja.interfaces.IBaseRepository

class UnidadMonetariaRepository(private val dao: IUnidadMonetariaDao): IBaseRepository<UnidadMonetariaEntity> {

    //val allWords: Flow<List<UnidadMonetariaEntity>> = dao.

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertar(mTEntity: UnidadMonetariaEntity) {
        dao.insertar(mTEntity)
    }

    override fun leerPorId( id: Int): Flow<UnidadMonetariaEntity> {
        TODO("Not yet implemented")
    }

    override fun leerPorId( id: String): Flow<UnidadMonetariaEntity> {
        TODO("Not yet implemented")
    }

    override fun leerTodo(): Flow<List<UnidadMonetariaEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun borrar( mTEntity: UnidadMonetariaEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun actualizar( mTEntity: UnidadMonetariaEntity) {
        TODO("Not yet implemented")
    }
}