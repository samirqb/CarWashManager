package samirqb.lib.rrhh

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import samirqb.lib.rrhh.daos.IAcuerdoDePagoNominaDao
import samirqb.lib.rrhh.daos.IOperarioYContratoDao
import samirqb.lib.rrhh.daos.IPeriodoFacturacionNominaDao
import samirqb.lib.rrhh.daos.IPlanDeCompensacionEconomicaDao
import samirqb.lib.rrhh.entities.AcuerdoDePagoNominaEntity
import samirqb.lib.rrhh.entities.OperarioYContratoEntity
import samirqb.lib.rrhh.entities.PeriodoFacturacionNominaEntity
import samirqb.lib.rrhh.entities.PlanDeCompensacionEconomicaEntity
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter

@Database(
    entities = [
        AcuerdoDePagoNominaEntity::class,
        OperarioYContratoEntity::class,
        PeriodoFacturacionNominaEntity::class,
        PlanDeCompensacionEconomicaEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabaseRRHH:RoomDatabase() {
    abstract fun iAcuerdoDePagoNominaDao(): IAcuerdoDePagoNominaDao
    abstract fun iOperarioYContratoDao(): IOperarioYContratoDao
    abstract fun iPeriodoFacturacionNominaDao(): IPeriodoFacturacionNominaDao
    abstract fun iPlanDeCompensacionEconomicaDao(): IPlanDeCompensacionEconomicaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabaseRRHH? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabaseRRHH {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AppDatabaseRRHH::class.java,
                    name = "db_modulo_rrhh"
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

                        var iPlanDeCompensacionEconomicaDao = database.iPlanDeCompensacionEconomicaDao()
                        var iPeriodoFacturacionNominaDao = database.iPeriodoFacturacionNominaDao()

                        iPlanDeCompensacionEconomicaDao.borrarTodo()
                        iPeriodoFacturacionNominaDao.borrarTodo()

                        var mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "COMISION FIJA POR VENTA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "COMISION PORCENTUAL POR VENTA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "COMISION FIJA POR SERVICIO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "COMISION PORCENTUAL POR SERVICIO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "SALARIO FIJO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "SALARIO BASE",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "BONIFICACION POR DESEMPEÑO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)

                        mPlanDeCompensacionEconomicaEntity = PlanDeCompensacionEconomicaEntity(
                            id_plan_compensacion_pk = 0,
                            descripcion_plan_compensacion = "PAGO POR HORA LABORADA",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPlanDeCompensacionEconomicaDao.insertar(mPlanDeCompensacionEconomicaEntity)


                        /** PERIODOD DE PAGO */
                        var mPeriodoFacturacionNominaEntiry = PeriodoFacturacionNominaEntity(
                            id_periodo_facturacion_pk = 0,
                            descripcion_del_periodo = "DIARIO",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPeriodoFacturacionNominaDao.insertar(mPeriodoFacturacionNominaEntiry)

                        mPeriodoFacturacionNominaEntiry = PeriodoFacturacionNominaEntity(
                            id_periodo_facturacion_pk = 0,
                            descripcion_del_periodo = "SEMANAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPeriodoFacturacionNominaDao.insertar(mPeriodoFacturacionNominaEntiry)

                        mPeriodoFacturacionNominaEntiry = PeriodoFacturacionNominaEntity(
                            id_periodo_facturacion_pk = 0,
                            descripcion_del_periodo = "QUINCENAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPeriodoFacturacionNominaDao.insertar(mPeriodoFacturacionNominaEntiry)

                        mPeriodoFacturacionNominaEntiry = PeriodoFacturacionNominaEntity(
                            id_periodo_facturacion_pk = 0,
                            descripcion_del_periodo = "MENSUAL",
                            fecha_hora_creacion = datetime.toString()
                        )
                        iPeriodoFacturacionNominaDao.insertar(mPeriodoFacturacionNominaEntiry)
                    }
                }
            }
        }
    }
}