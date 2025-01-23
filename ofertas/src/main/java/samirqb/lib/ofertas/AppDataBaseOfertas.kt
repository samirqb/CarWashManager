package samirqb.lib.ofertas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import samirqb.lib.ofertas.daos.IPrecioDao
import samirqb.lib.ofertas.daos.IPreciosDeServiciosDao
import samirqb.lib.ofertas.daos.IServicioDao
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.entities.PrecioDeProductoEntity
import samirqb.lib.ofertas.entities.PrecioDeServicioEntity
import samirqb.lib.ofertas.entities.ProductoEntity
import samirqb.lib.ofertas.entities.ServicioEntity

@Database(
    entities = [
        PrecioEntity::class,
        ProductoEntity::class,
        ServicioEntity::class,
        PrecioDeServicioEntity::class,
        PrecioDeProductoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBaseOfertas: RoomDatabase()  {

    abstract fun iServicioDao(): IServicioDao
    abstract fun iPrecioDao(): IPrecioDao
    abstract fun iPrecioDeServicioDao(): IPreciosDeServiciosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBaseOfertas? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDataBaseOfertas {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDataBaseOfertas::class.java,
                    name = "db_modulo_ofertas"
                )
                    //.addCallback(PrePopulateDatabaseCallback(scope))
                    .build()
                INSTANCE = db
                db
            }
        }

        /*
        // PRE-POPULATE BASE DE DATOS CAJA
        private class PrePopulateDatabaseCallback(
            private val scope: CoroutineScope,
        ) : RoomDatabase.Callback() {

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {

                        val datetime = now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

                        var uMDao = database.iUnidadMonetariaDao()
                        var tMDao = database.iTipoMonedaDao()

                        uMDao.borrarTodo()
                        tMDao.borrarTodo()

                        var mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "XXX",
                            nombre_y_origen = "OTRA MONEDA",
                            fecha_hora_creacion = datetime.toString()
                        )

                        uMDao.insertar(mUMEntity)


                        var mTMEntity = TipoMonedaEntity(
                            tipo_pk = "BILLETE",
                            fecha_hora_creacion = datetime.toString()
                        )

                        tMDao.insertar(mTMEntity)
                    }
                }
            }
        }
        */
    }
}