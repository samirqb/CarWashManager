package samirqb.lib.transacciones.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import samirqb.lib.transacciones.entities.MedioDePagoEntity
import samirqb.lib.transacciones.interfaces.IBaseDao

@Dao
interface IMedioDePagoDao:IBaseDao<MedioDePagoEntity> {

    @Transaction
    @Query( "SELECT * FROM tab_medios_de_pagos ORDER BY id_medio_de_pago_pk ASC" )
    fun leerTodo(): Flow<List<MedioDePagoEntity>>

    @Transaction
    @Query( "SELECT * FROM tab_medios_de_pagos WHERE id_medio_de_pago_pk = :id" )
    fun leerPorId(id: Int): Flow<MedioDePagoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_medios_de_pagos WHERE id_medio_de_pago_pk = :id" )
    fun leerPorId(id: Float): Flow<MedioDePagoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_medios_de_pagos WHERE id_medio_de_pago_pk = :id" )
    fun leerPorId(id: String): Flow<MedioDePagoEntity>

    @Transaction
    @Query( "SELECT * FROM tab_medios_de_pagos ORDER BY id_medio_de_pago_pk DESC LIMIT 1 " )
    fun leerMasReciente(): Flow<MedioDePagoEntity?>

    @Query( "DELETE FROM tab_medios_de_pagos" )
    suspend fun borrarTodo()
    
}