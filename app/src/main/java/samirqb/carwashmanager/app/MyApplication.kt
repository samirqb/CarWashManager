package samirqb.carwashmanager.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samirqb.lib.caja.AppDatabaseCaja
import samirqb.lib.caja.repositories.UnidadMonetariaRepository

class MyApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database_caja by lazy { AppDatabaseCaja.getDatabase(this, applicationScope) }
    val repository_unidad_monetaria by lazy { UnidadMonetariaRepository(database_caja.iUnidadMonetariaDao()) }
}