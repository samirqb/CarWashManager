package samirqb.lib.personas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import samirqb.lib.personas.daos.IClienteDao
import samirqb.lib.personas.daos.IOperarioDao
import samirqb.lib.personas.entities.ClienteEntity
import samirqb.lib.personas.entities.OperarioEntity

@Database(
    entities = [
        ClienteEntity::class,
        OperarioEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabasePersonas: RoomDatabase() {

    abstract fun iClienteDao(): IClienteDao
    abstract fun iOperarioDao(): IOperarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabasePersonas? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabasePersonas {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabasePersonas::class.java,
                    name = "db_modulo_personas"
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