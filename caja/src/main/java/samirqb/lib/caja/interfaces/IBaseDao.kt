package samirqb.lib.caja.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

interface IBaseDao<TEntity>{
    @Insert
    suspend fun insertar( mTEntity: TEntity)

    @Update
    suspend fun actualizar( mTEntity: TEntity)

    @Delete
    suspend fun borrar( mTEntity: TEntity)

}