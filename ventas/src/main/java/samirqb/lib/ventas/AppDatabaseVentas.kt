package samirqb.lib.ventas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import samirqb.lib.ventas.daos.IDetalleOrdenProductoDao
import samirqb.lib.ventas.daos.IDetalleOrdenServicioDao
import samirqb.lib.ventas.daos.IOrdenDeVentaDao
import samirqb.lib.ventas.entities.DetalleOrdenProductoEntity
import samirqb.lib.ventas.entities.DetalleOrdenServicioEntity
import samirqb.lib.ventas.entities.OrdenDeVentaEntity

@Database(
    entities = [
        DetalleOrdenProductoEntity::class,
        DetalleOrdenServicioEntity::class,
        OrdenDeVentaEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabaseVentas: RoomDatabase()  {

    abstract fun iIDetalleOrdenProductoDao(): IDetalleOrdenProductoDao
    abstract fun iDetalleOrdenServicioDao(): IDetalleOrdenServicioDao
    abstract fun iOrdenDeVentaDao(): IOrdenDeVentaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseVentas? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabaseVentas {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabaseVentas::class.java,
                    name = "db_modulo_ventas"
                )
                    //.addCallback(PrePopulateDatabaseCallback(scope))
                    .build()
                INSTANCE = db
                db
            }
        }
    }
}