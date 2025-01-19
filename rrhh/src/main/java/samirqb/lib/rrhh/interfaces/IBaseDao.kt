package samirqb.lib.rrhh.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface IBaseDao<TEntity>{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar( mTEntity: TEntity)

    @Update
    suspend fun actualizar( mTEntity: TEntity)

    @Delete
    suspend fun borrar( mTEntity: TEntity)
}