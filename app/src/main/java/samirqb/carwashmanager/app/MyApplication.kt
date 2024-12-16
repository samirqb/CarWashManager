package samirqb.carwashmanager.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samirqb.lib.caja.AppDatabaseCaja
import samirqb.lib.caja.repositories.DenominacionMonedaRepository
import samirqb.lib.caja.repositories.MonedaRepository
import samirqb.lib.caja.repositories.TipoMonedaRepository
import samirqb.lib.caja.repositories.UnidadMonetariaRepository

class MyApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val mAppDatabaseCaja by lazy { AppDatabaseCaja.getDatabase(this, applicationScope) }

    //   R E P O S I T O R I O S
    val mUnidadMonetariaRepository by lazy { UnidadMonetariaRepository(mAppDatabaseCaja.iUnidadMonetariaDao()) }
    val mTipoMonedaRepository by lazy { TipoMonedaRepository(mAppDatabaseCaja.iTipoMonedaDao()) }
    val mDenominacionMonedaRepository by lazy { DenominacionMonedaRepository(mAppDatabaseCaja.iDenominacionMonedaDao()) }
    val mMonedaRepository by lazy { MonedaRepository(mAppDatabaseCaja.iMoneda()) }

    companion object {
        val APPLICATION_KEY = "mApplicationKey" // Unique key for application access
    }
}