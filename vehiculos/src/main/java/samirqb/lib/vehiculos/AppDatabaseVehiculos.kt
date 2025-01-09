package samirqb.lib.vehiculos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [
        //ClasificacionDelVehiculoEntity::class,
        //VehiculoEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabaseVehiculos:RoomDatabase() {
    //abstract fun iClasificacionDelVehiculoDao(): IClasificacionDelVehiculoDao
    //abstract fun iVehiculoDao(): IVehiculoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseVehiculos? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabaseVehiculos {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabaseVehiculos::class.java,
                    name = "db_modulo_vehiculos"
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