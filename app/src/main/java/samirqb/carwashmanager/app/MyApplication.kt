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
import samirqb.lib.ofertas.AppDataBaseOfertas
import samirqb.lib.ofertas.repositories.PrecioRepository
import samirqb.lib.ofertas.repositories.ServiciosRepository
import samirqb.lib.ofertas.repositories.ServiciosYPreciosRepository
import samirqb.lib.ofertas.uc.AgregarServicioUseCase
import samirqb.lib.ofertas.uc.AgregarServicioYPrecioUseCase
import samirqb.lib.ofertas.uc.AgregarPrecioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosPreciosUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosActivosUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosConNomnreUseCase
import samirqb.lib.ofertas.uc.ListarTodosLosServiciosYPreciosUseCase
import samirqb.lib.ofertas.uc.ObtenerElPrecioMasRecienteUseCase
import samirqb.lib.pagos.AppDatabasePagos
import samirqb.lib.pagos.repositories.OrdenPagoNominaRepositories
import samirqb.lib.pagos.uc.AgregarOrdenDePagoNominaUseCase
import samirqb.lib.pagos.uc.ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase
import samirqb.lib.pagos.uc.ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase
import samirqb.lib.pagos.uc.ListarTodasLasOrdenesDePagoNominaUseCase
import samirqb.lib.pagos.uc.ObtenerOrdenDePagoNominaMasRecienteUseCase
import samirqb.lib.personas.AppDatabasePersonas
import samirqb.lib.personas.uc.AgregarClienteUseCase
import samirqb.lib.personas.uc.AgregarOperarioUseCase
import samirqb.lib.personas.uc.ListarTodosLosClientesUseCase
import samirqb.lib.personas.uc.ListarTodosLosOperariosUseCase
import samirqb.lib.personas.repositories.ClienteRepository
import samirqb.lib.personas.repositories.OperarioRepository
import samirqb.lib.personas.uc.BuscarClientePorIdUseCase
import samirqb.lib.personas.uc.ListarTodosLosOperariosActivosUseCase
import samirqb.lib.vehiculos.AppDatabaseVehiculos
import samirqb.lib.vehiculos.repositories.ClasificacionDelVehiculoRepository
import samirqb.lib.vehiculos.repositories.VehiculoRepository
import samirqb.lib.vehiculos.uc.AgregarClasificacionDelVehiculoUseCase
import samirqb.lib.vehiculos.uc.AgregarVehiculoUseCase
import samirqb.lib.vehiculos.uc.BuscarVehiculoPorMatriculaUseCase
import samirqb.lib.vehiculos.uc.ListarTodasLasClasificacionesDelVehiculoUseCase
import samirqb.lib.vehiculos.uc.ListarTodosLosVehiculosUseCase
import samirqb.lib.ventas.AppDatabaseVentas
import samirqb.lib.ventas.repositories.OrdenDeVentaRepository
import samirqb.lib.ventas.uc.CrearNuevaOrdenDeVentaUseCase
import samirqb.lib.ventas.uc.ListarTodasLasOrdenesDeVentasUseCase
import samirqb.lib.ventas.uc.ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase
import samirqb.lib.ventas.uc.ObtenerOrdenDeVentaMasRecienteUseCase

class MyApplication: Application() {


    private val applicationScope = CoroutineScope(SupervisorJob())


    //   I N S T A C I A S   D E   B A S E S   D E   D A T O S
    private val mAppDatabaseCaja by lazy { AppDatabaseCaja.getDatabase(this, applicationScope) }
    private val mAppDatabasePersonas by lazy { AppDatabasePersonas.getDatabase(this, applicationScope) }
    private val mAppDatabaseOfertas by lazy { AppDataBaseOfertas.getDatabase(this, applicationScope) }
    private val mAppDatabaseVehiculos by lazy { AppDatabaseVehiculos.getDatabase(this, applicationScope) }
    private val mAppDatabasePagos by lazy { AppDatabasePagos.getDatabase(this, applicationScope) }
    private val mAppDatabaseVentas by lazy { AppDatabaseVentas.getDatabase(this, applicationScope) }


    //   R E P O   M O D U L O   C A J A
    val mUnidadMonetariaRepository by lazy { UnidadMonetariaRepository(mAppDatabaseCaja.iUnidadMonetariaDao()) }
    val mTipoMonedaRepository by lazy { TipoMonedaRepository(mAppDatabaseCaja.iTipoMonedaDao()) }
    val mDenominacionMonedaRepository by lazy { DenominacionMonedaRepository(mAppDatabaseCaja.iDenominacionMonedaDao()) }
    val mMonedaRepository by lazy { MonedaRepository(mAppDatabaseCaja.iMonedaDao()) }
    val mAperturaCajaRepository by lazy { AperturaCajaRepository(mAppDatabaseCaja.iAperturaCajaDao()) }
    val mCierreCajaRepository by lazy { CierreCajaRepository(mAppDatabaseCaja.iCierreCajaDao()) }
    val mDetalleAperturaCajaRepository by lazy { DetalleAperturaCajaRepository(mAppDatabaseCaja.iDetalleAperturaCaja()) }
    val mDetalleCierreCajaRepository by lazy { DetalleCierreCajaRepository(mAppDatabaseCaja.iDetalleCierreCajaDao()) }


    //   U C   M O D U L O   P E R S O N A S
    //   ---> CLIENTES
    val mAgregarClienteUseCase by lazy { AgregarClienteUseCase( ClienteRepository(mAppDatabasePersonas.iClienteDao()) ) }
    val mListarTodosLosClientesUseCase by lazy { ListarTodosLosClientesUseCase(ClienteRepository(mAppDatabasePersonas.iClienteDao())) }
    val mBuscarClientePorIdUseCase by lazy { BuscarClientePorIdUseCase(ClienteRepository(mAppDatabasePersonas.iClienteDao())) }

    //   ---> OPERARIOS
    val mAgregarOperarioUseCase by lazy { AgregarOperarioUseCase( OperarioRepository(mAppDatabasePersonas.iOperarioDao()) ) }
    val mListarTodosLosOperariosUseCase by lazy { ListarTodosLosOperariosUseCase(OperarioRepository(mAppDatabasePersonas.iOperarioDao())) }
    val mListarTodosLosOperariosActivosUseCase by lazy { ListarTodosLosOperariosActivosUseCase(OperarioRepository(mAppDatabasePersonas.iOperarioDao())) }


    //   U C   O F E R T A S
    val mAgregarServicioUseCase by lazy { AgregarServicioUseCase(ServiciosRepository(mAppDatabaseOfertas.iServicioDao())) }
    val mListarTodosLosServiciosUseCase by lazy { ListarTodosLosServiciosUseCase(ServiciosRepository(mAppDatabaseOfertas.iServicioDao())) }
    val mAgregarServicioYPrecioUseCase by lazy { AgregarServicioYPrecioUseCase( ServiciosYPreciosRepository(mAppDatabaseOfertas.iServicioYPrecioDao())) }
    val mListarTodosLosServiciosYPreciosUseCase by lazy { ListarTodosLosServiciosYPreciosUseCase( ServiciosYPreciosRepository(mAppDatabaseOfertas.iServicioYPrecioDao())) }
    val mListarTodosLosServiciosYPreciosConNomnreUseCase by lazy { ListarTodosLosServiciosYPreciosConNomnreUseCase( ServiciosYPreciosRepository(mAppDatabaseOfertas.iServicioYPrecioDao())) }
    val mListarTodosLosServiciosYPreciosActivosUseCase by lazy { ListarTodosLosServiciosYPreciosActivosUseCase(ServiciosYPreciosRepository(mAppDatabaseOfertas.iServicioYPrecioDao())) }
    val mListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase by lazy { ListarTodosLosServiciosYPreciosActivosConNombreDelServicioUseCase(ServiciosYPreciosRepository(mAppDatabaseOfertas.iServicioYPrecioDao())) }
    val mAgregarPrecioUseCase by lazy { AgregarPrecioUseCase( PrecioRepository(mAppDatabaseOfertas.iPrecioDao())) }
    val mListarTodosLosPreciosUseCase by lazy { ListarTodosLosPreciosUseCase(PrecioRepository(mAppDatabaseOfertas.iPrecioDao())) }
    val mObtenerElPrecioMasRecienteUseCase by lazy { ObtenerElPrecioMasRecienteUseCase(PrecioRepository(mAppDatabaseOfertas.iPrecioDao())) }


    //   U C   V E H I C U L O S
    val mAgregarClasificacionDelVehiculoUseCase by lazy { AgregarClasificacionDelVehiculoUseCase( ClasificacionDelVehiculoRepository(mAppDatabaseVehiculos.iClasificacionDelVehiculoDao())) }
    val mListarTodasLasClasificacionesDelVehiculoUseCase by lazy{ ListarTodasLasClasificacionesDelVehiculoUseCase(ClasificacionDelVehiculoRepository(mAppDatabaseVehiculos.iClasificacionDelVehiculoDao())) }
    val mAgregarVehiculoUseCase by lazy{ AgregarVehiculoUseCase(VehiculoRepository(mAppDatabaseVehiculos.iVehiculoDao())) }
    val mListarTodosLosVehiculosUseCase by lazy{ ListarTodosLosVehiculosUseCase(VehiculoRepository(mAppDatabaseVehiculos.iVehiculoDao())) }
    val mBuscarVehiculoPorMatriculaUseCase by lazy{ BuscarVehiculoPorMatriculaUseCase(VehiculoRepository(mAppDatabaseVehiculos.iVehiculoDao())) }


    //   U C   O R D E N E S   D E   P A G O   N O M I N A
    val mAgregarOrdenDePagoNominaUseCase by lazy{ AgregarOrdenDePagoNominaUseCase(OrdenPagoNominaRepositories(mAppDatabasePagos.iOrdenPagoNominaDao())) }
    val mListarTodasLasOrdenesDePagoNominaUseCase by lazy{ ListarTodasLasOrdenesDePagoNominaUseCase(OrdenPagoNominaRepositories(mAppDatabasePagos.iOrdenPagoNominaDao())) }
    val mListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase by lazy{ ListarTodasLasOrdenesDePagoNominaPorEstadoDePagoUseCase(OrdenPagoNominaRepositories(mAppDatabasePagos.iOrdenPagoNominaDao())) }
    val mListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase by lazy{ ListarOrdenesDePagoPorOperarioIdYEstadoDePagoUseCase(OrdenPagoNominaRepositories(mAppDatabasePagos.iOrdenPagoNominaDao())) }
    val mObtenerOrdenDePagoNominaMasRecienteUseCase by lazy{ ObtenerOrdenDePagoNominaMasRecienteUseCase(OrdenPagoNominaRepositories(mAppDatabasePagos.iOrdenPagoNominaDao())) }


    //   U C   O R D E N E S   D E   V E N T A S
    val mCrearNuevaOrdenDeVentaUseCase by lazy{ CrearNuevaOrdenDeVentaUseCase(OrdenDeVentaRepository(mAppDatabaseVentas.iOrdenDeVentaDao())) }
    val mListarTodasLasOrdenesDeVentasUseCase by lazy{ ListarTodasLasOrdenesDeVentasUseCase(OrdenDeVentaRepository(mAppDatabaseVentas.iOrdenDeVentaDao())) }
    val mObtenerOrdenDeVentaMasRecienteUseCase by lazy{ ObtenerOrdenDeVentaMasRecienteUseCase(OrdenDeVentaRepository(mAppDatabaseVentas.iOrdenDeVentaDao())) }
    val mObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase by lazy{ ObtenerCantidadDeRegistrosDeOrdenesDeVentaUseCase(OrdenDeVentaRepository(mAppDatabaseVentas.iOrdenDeVentaDao())) }

    companion object {
        val APPLICATION_KEY = "mApplicationKey" // Unique key for application access
    }
}