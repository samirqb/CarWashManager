package samirqb.lib.vehiculos

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import samirqb.lib.vehiculos.dao.IClasificacionDelVehiculoDao
import samirqb.lib.vehiculos.dao.IVehiculoDao
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity
import samirqb.lib.vehiculos.entities.VehiculoEntity
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

@Database(
    entities = [
        ClasificacionDelVehiculoEntity::class,
        VehiculoEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabaseVehiculos : RoomDatabase() {
    abstract fun iClasificacionDelVehiculoDao(): IClasificacionDelVehiculoDao
    abstract fun iVehiculoDao(): IVehiculoDao

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
                    .addCallback(PrePopulateDatabaseCallback(scope))
                    .build()
                INSTANCE = db
                db
            }
        }

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

                        var iClasificacionDelVehiculoDao = database.iClasificacionDelVehiculoDao()

                        try {
                            iClasificacionDelVehiculoDao.borrarTodo()

                            ClasificacionesDeVehiculosParams().obtener().forEach{
                                iClasificacionDelVehiculoDao.insertar(
                                    ClasificacionDelVehiculoEntity(
                                        clase_id_pk = 0,
                                        descripcion = it,
                                        fecha_hora_creacion = datetime
                                    )
                                )
                            }
                        } catch (e:Exception){
                            throw CustomDatabaseException("Error en iClasificacionDelVehiculoDao.insertar desde PrePopulateDatabaseCallback()", e)
                        }
                    }
                }
            }
        }

    }
}