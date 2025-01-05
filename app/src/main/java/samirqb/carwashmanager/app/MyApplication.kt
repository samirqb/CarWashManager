package samirqb.carwashmanager.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import samirqb.lib.caja.AppDatabaseCaja
import samirqb.lib.caja.repositories.AperturaCajaRepository
import samirqb.lib.caja.repositories.CierreCajaRepository
import samirqb.lib.caja.repositories.DenominacionMonedaRepository
import samirqb.lib.caja.repositories.DetalleAperturaCajaRepository
import samirqb.lib.caja.repositories.DetalleCierreCajaRepository
import samirqb.lib.caja.repositories.MonedaRepository
import samirqb.lib.caja.repositories.TipoMonedaRepository
import samirqb.lib.caja.repositories.UnidadMonetariaRepository
import samirqb.lib.personas.AppDatabasePersonas
import samirqb.lib.personas.cu.AgregarClienteUseCase
import samirqb.lib.personas.repositories.ClienteRepository

class MyApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val mAppDatabaseCaja by lazy { AppDatabaseCaja.getDatabase(this, applicationScope) }
    private val mAppDatabasePersonas by lazy { AppDatabasePersonas.getDatabase(this, applicationScope) }


    //   R E P   M O D U L O   C A J A
    val mUnidadMonetariaRepository by lazy { UnidadMonetariaRepository(mAppDatabaseCaja.iUnidadMonetariaDao()) }
    val mTipoMonedaRepository by lazy { TipoMonedaRepository(mAppDatabaseCaja.iTipoMonedaDao()) }
    val mDenominacionMonedaRepository by lazy { DenominacionMonedaRepository(mAppDatabaseCaja.iDenominacionMonedaDao()) }
    val mMonedaRepository by lazy { MonedaRepository(mAppDatabaseCaja.iMonedaDao()) }
    val mAperturaCajaRepository by lazy { AperturaCajaRepository(mAppDatabaseCaja.iAperturaCajaDao()) }
    val mCierreCajaRepository by lazy { CierreCajaRepository(mAppDatabaseCaja.iCierreCajaDao()) }
    val mDetalleAperturaCajaRepository by lazy { DetalleAperturaCajaRepository(mAppDatabaseCaja.iDetalleAperturaCaja()) }
    val mDetalleCierreCajaRepository by lazy { DetalleCierreCajaRepository(mAppDatabaseCaja.iDetalleCierreCajaDao()) }


    //   R E P   M O D U L O   P E R S O N A S
    private val mClienteRepository by lazy { ClienteRepository(mAppDatabasePersonas.iClienteDao()) }
    private val mOperarioRepository by lazy { ClienteRepository(mAppDatabasePersonas.iOperarioDao()) }

    //   U C   M O D U L O   P E R S O N A S
    val AgregarClienteUseCase by lazy { AgregarClienteUseCase( ClienteRepository(mAppDatabasePersonas.iClienteDao()) ) }
    val AgregarOperarioUseCase by lazy { AgregarClienteUseCase( ClienteRepository(mAppDatabasePersonas.iClienteDao()) ) }

    companion object {
        val APPLICATION_KEY = "mApplicationKey" // Unique key for application access
    }
}