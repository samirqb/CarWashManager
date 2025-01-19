package samirqb.lib.transacciones

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import samirqb.lib.transacciones.daos.IMedioDePagoDao
import samirqb.lib.transacciones.daos.ITipoTransaccionDao
import samirqb.lib.transacciones.daos.ITransaccionDao
import samirqb.lib.transacciones.entities.MedioDePagoEntity
import samirqb.lib.transacciones.entities.TipoTransaccionEntity
import samirqb.lib.transacciones.entities.TransaccionEntity
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

@Database(
    entities = [
        MedioDePagoEntity::class,
        TipoTransaccionEntity::class,
        TransaccionEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabaseTransacciones: RoomDatabase() {

    abstract fun iMedioDePagoDao(): IMedioDePagoDao
    abstract fun iTransaccionDao(): ITransaccionDao
    abstract fun iTipoTransaccionDao(): ITipoTransaccionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseTransacciones? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabaseTransacciones {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabaseTransacciones::class.java,
                    name = "db_modulo_transacciones"
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

                        var uTTDao = database.iTipoTransaccionDao()
                        var tMDPDao = database.iMedioDePagoDao()

                        uTTDao.borrarTodo()
                        tMDPDao.borrarTodo()

                        /** TRANSACCIONES FINACIERAS
                        VENTA - PAGOS - COMPRAS - COBROS */
                        var mTipoTransaccionEntity = TipoTransaccionEntity(
                            id_tipo_transaccion_pk = 1,
                            descripcion = "VENTA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uTTDao.insertar(mTipoTransaccionEntity)

                        mTipoTransaccionEntity = TipoTransaccionEntity(
                            id_tipo_transaccion_pk = 2,
                            descripcion = "PAGO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        uTTDao.insertar(mTipoTransaccionEntity)


                        /** MEDIOS DE PAGO */
                        var mMDPEntity = MedioDePagoEntity(
                            id_medio_de_pago_pk = 1,
                            descripcion = "EFECTIVO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDPDao.insertar(mMDPEntity)

                        mMDPEntity = MedioDePagoEntity(
                            id_medio_de_pago_pk = 2,
                            descripcion = "TARJETA CREDITO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDPDao.insertar(mMDPEntity)

                        mMDPEntity = MedioDePagoEntity(
                            id_medio_de_pago_pk = 3,
                            descripcion = "TARJETA DEBITO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDPDao.insertar(mMDPEntity)

                        mMDPEntity = MedioDePagoEntity(
                            id_medio_de_pago_pk = 4,
                            descripcion = "TRANSFERENCIA BANCARIA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDPDao.insertar(mMDPEntity)

                        mMDPEntity = MedioDePagoEntity(
                            id_medio_de_pago_pk = 5,
                            descripcion = "TRANSFERENCIA MOVIL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        tMDPDao.insertar(mMDPEntity)
                    }
                }
            }
        }
    }
}