package samirqb.lib.pagos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import samirqb.lib.pagos.daos.IOrdenPagoNominaDao
import samirqb.lib.pagos.entities.DatosTotalesOrdenPagoNominaEntity
import samirqb.lib.pagos.entities.OrdenPagoNominaEntity

@Database(
    entities = [
        OrdenPagoNominaEntity::class,
        DatosTotalesOrdenPagoNominaEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabasePagos:RoomDatabase() {

    abstract fun iOrdenPagoNominaDao(): IOrdenPagoNominaDao
    //abstract fun iDatosTotalesOrdenPagoNominaDao(): IDatosTotalesOrdenPagoNominaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabasePagos? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabasePagos {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabasePagos::class.java,
                    name = "db_modulo_pagos"
                )
                    //.addCallback(PrePopulateDatabaseCallback(scope))
                    .build()
                INSTANCE = db
                db
            }
        }
    }
}