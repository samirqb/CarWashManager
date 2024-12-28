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

                        uMDao.borrarTodo()
                        tMDao.borrarTodo()

                        var mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "XXX",
                            nombre_y_origen = "OTRA MONEDA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "ARS",
                            nombre_y_origen = "ARGENTINE PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "BOB",
                            nombre_y_origen = "BOLIVIANO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "CLP",
                            nombre_y_origen = "CHILEAN PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "COP",
                            nombre_y_origen = "COLOMBIAN PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "CRC",
                            nombre_y_origen = "COSTA RICAN COLON",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "CUP",
                            nombre_y_origen = "CUBAN PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "DOP",
                            nombre_y_origen = "DOMINICAN PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "SVC",
                            nombre_y_origen = "EL SALVADOR COLON",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "GTQ",
                            nombre_y_origen = "QUETZAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "GYD",
                            nombre_y_origen = "GUYANA DOLLAR",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "HNL",
                            nombre_y_origen = "LEMPIRA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "MXN",
                            nombre_y_origen = "MEXICAN PESO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "NIO",
                            nombre_y_origen = "CORDOBA ORO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "PAB",
                            nombre_y_origen = "BALBOA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "PYG",
                            nombre_y_origen = "GUARANI",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "PEN",
                            nombre_y_origen = "SOL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "USD",
                            nombre_y_origen = "US DOLLAR",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "UYU",
                            nombre_y_origen = "PESO URUGUAYO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "VED",
                            nombre_y_origen = "BOLÍVAR SOBERANO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "VES",
                            nombre_y_origen = "BOLÍVAR SOBERANO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "EUR",
                            nombre_y_origen = "EURO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)
                        mUMEntity = UnidadMonetariaEntity(
                            codigo_iso_4217_pk = "BRL",
                            nombre_y_origen = "BRAZILIAN REAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uMDao.insertar(mUMEntity)

                        var mTMEntity = TipoMonedaEntity(
                            tipo_pk = "BILLETE",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDao.insertar(mTMEntity)
                        mTMEntity = TipoMonedaEntity(
                            tipo_pk = "MONEDA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDao.insertar(mTMEntity)
                        mTMEntity = TipoMonedaEntity(
                            tipo_pk = "DIGITAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDao.insertar(mTMEntity)
                    }
                }
            }
        }
    }
}
