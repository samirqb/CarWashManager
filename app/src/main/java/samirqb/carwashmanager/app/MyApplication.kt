package samirqb.carwashmanager.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samirqb.lib.caja.AppDatabaseCaja
import samirqb.lib.caja.repositories.UnidadMonetariaRepository

class MyApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val mAppDatabaseCaja by lazy { AppDatabaseCaja.getDatabase(this, applicationScope) }

    val mUnidadMonetariaRepository by lazy { UnidadMonetariaRepository(mAppDatabaseCaja.iUnidadMonetariaDao()) }

    companion object {
        val APPLICATION_KEY = "mApplicationKey" // Unique key for application access
    }
}