package samirqb.lib.caja

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import samirqb.lib.caja.daos.IAperturaCajaDao
import samirqb.lib.caja.daos.ICierreCajaDao
import samirqb.lib.caja.daos.IDenominacionMonedaDao
import samirqb.lib.caja.daos.IDetalleAperturaCajaDao
import samirqb.lib.caja.daos.IDetalleCierreCajaDao
import samirqb.lib.caja.daos.IMonedaDao
import samirqb.lib.caja.daos.ITipoMonedaDao
import samirqb.lib.caja.daos.IUnidadMonetariaDao
import samirqb.lib.caja.entidades.AperturaCajaEntity
import samirqb.lib.caja.entidades.CierreCajaEntity
import samirqb.lib.caja.entidades.DenominacionMonedaEntity
import samirqb.lib.caja.entidades.DetalleAperturaCajaEntity
import samirqb.lib.caja.entidades.DetalleCierreCajaEntity
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.caja.entidades.TipoMonedaEntity
import samirqb.lib.caja.entidades.UnidadMonetariaEntity
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

@Database(
    entities = [
        UnidadMonetariaEntity::class,
        TipoMonedaEntity::class,
        DenominacionMonedaEntity::class,
        MonedaEntity::class,
        AperturaCajaEntity::class,
        DetalleAperturaCajaEntity::class,
        CierreCajaEntity::class,
        DetalleCierreCajaEntity::class,
    ], version = 1, exportSchema = false
)
abstract class AppDatabaseCaja : RoomDatabase() {

    abstract fun iUnidadMonetariaDao(): IUnidadMonetariaDao
    abstract fun iTipoMonedaDao(): ITipoMonedaDao
    abstract fun iDenominacionMonedaDao(): IDenominacionMonedaDao
    abstract fun iMonedaDao(): IMonedaDao
    abstract fun iAperturaCajaDao(): IAperturaCajaDao
    abstract fun iCierreCajaDao(): ICierreCajaDao
    abstract fun iDetalleAperturaCaja(): IDetalleAperturaCajaDao
    abstract fun iDetalleCierreCajaDao(): IDetalleCierreCajaDao

    /*
    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java,
        "db_modulo_caja"
    ).build()
    */

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseCaja? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabaseCaja {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabaseCaja::class.java,
                    name = "db_modulo_caja"
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

                        var uMDao = database.iUnidadMonetariaDao()
                        var tMDao = database.iTipoMonedaDao()
                        var dMDao = database.iDenominacionMonedaDao()
                        var mMDao = database.iMonedaDao()

                        try {
                            uMDao.borrarTodo()

                            UnidadesMonetariasParams().obtener().forEach {
                                uMDao.insertar(
                                    UnidadMonetariaEntity(
                                        codigo_iso_4217_pk = it.key,
                                        nombre_y_origen = it.value,
                                        fecha_hora_creacion = datetime
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            //throw CustomDatabaseException("Error en iClasificacionDelVehiculoDao.insertar desde PrePopulateDatabaseCallback()", e)
                            throw Exception(
                                "Error en iUnidadMonetariaDao.insertar desde PrePopulateDatabaseCallback()",
                                e
                            )
                        }

                        try {
                            tMDao.borrarTodo()

                            FormatoDeEmisionDeDineroParams().obtener().forEach {
                                tMDao.insertar(
                                    TipoMonedaEntity(
                                        tipo_pk = it,
                                        fecha_hora_creacion = datetime
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            //throw CustomDatabaseException("Error en iClasificacionDelVehiculoDao.insertar desde PrePopulateDatabaseCallback()", e)
                            throw Exception(
                                "Error en iTipoMonedaDao.insertar desde PrePopulateDatabaseCallback()",
                                e
                            )
                        }

                        try {
                            dMDao.borrarTodo()

                            TodasLasDenominacionesParams().obtener().forEach {
                                dMDao.insertar(
                                    DenominacionMonedaEntity(
                                        denominacion_pk = it,
                                        fecha_hora_creacion = datetime
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            //throw CustomDatabaseException("Error en iClasificacionDelVehiculoDao.insertar desde PrePopulateDatabaseCallback()", e)
                            throw Exception(
                                "Error en iDenominacionMonedaDao.insertar desde PrePopulateDatabaseCallback()",
                                e
                            )
                        }

                        try {
                            mMDao.borrarTodo()

                            //COP
                            DenominacionesPorUnidadMonetariaParams().obtener_cop().forEach {
                                mMDao.insertar(
                                    MonedaEntity(
                                        id_moneda_pk = 0,
                                        codigo_iso_4217_fk = it[0],
                                        denominacion_fk = it[1].toFloat(),
                                        tipo_fk = it[2],
                                        fecha_hora_creacion = datetime
                                    )
                                )
                            }
                        } catch (e: Exception) {
                            //throw CustomDatabaseException("Error en iClasificacionDelVehiculoDao.insertar desde PrePopulateDatabaseCallback()", e)
                            throw Exception(
                                "Error en iMonedaDao.insertar desde PrePopulateDatabaseCallback()",
                                e
                            )
                        }
                    }
                }
            }
        }
    }
}
