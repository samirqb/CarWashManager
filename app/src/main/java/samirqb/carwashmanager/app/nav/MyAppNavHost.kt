package samirqb.carwashmanager.app.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import samirqb.carwashmanager.app.ui.dialogs.AgregarClasificacionVehiculoDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarClienteDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarMonedaDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarOperarioDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarNombreDelServicioDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarServicioYPrecioDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarVehiculoDialog
import samirqb.carwashmanager.app.ui.dialogs.AperturaCajaCantidadesPorDenominacionDialog
import samirqb.carwashmanager.app.ui.dialogs.AperturaCajaConfirmacionDialog
import samirqb.carwashmanager.app.ui.dialogs.BuscarClienteDialog
import samirqb.carwashmanager.app.ui.dialogs.CierreCajaCantidadesPorDenominacionDialog
import samirqb.carwashmanager.app.ui.dialogs.CierreCajaConfirmacionDialog
import samirqb.carwashmanager.app.ui.dialogs.NuevaOrdenDeVentaDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarServicioAOrdenDeVentaDialog
import samirqb.carwashmanager.app.ui.screens.AdministrarCategoriasScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarClientesScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarEmpleadosScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarMonedaScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarServiciosScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarVehiculosScreen
import samirqb.carwashmanager.app.ui.screens.InicioScreen
import samirqb.carwashmanager.app.ui.screens.NuevaOrdenDeVentaScreen
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.DenominacionMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.DetalleOrdenServicioViewModel
import samirqb.carwashmanager.app.viewmodels.MonedaViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDePagoNominaViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDeVentaViewModel
import samirqb.carwashmanager.app.viewmodels.PrecioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.carwashmanager.app.viewmodels.TipoMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.UnidadMonetariaViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel


/**   S C R E E N S   */
@Serializable
object InicioScreenRoute
//data class InicioScreenRoute(val name: String? = null)

@Serializable
object AdministrarMonedaScreenRoute
//data class AdministradorMonedaScreenRoute(val name: String? = null)

@Serializable
object AdministrarServiciosScreenRoute
//data class AdministrrServiciosScreenRoute(val name: String? = null)

@Serializable
object AdministrarEmpleadosScreenRoute
//data class AdministrarEmpleadosScreenRoute(val name: String? = null)

@Serializable
object AdministrarClientesScreenRoute
//data class AdministrarClientesScreenRoute(val name: String? = null)

@Serializable
object AdministrarClasificacionVehiculoScreenRoute
//data class AdministrarCategoriasScreenRoute(val name: String? = null)

@Serializable
object AdministrarVehiculosScreenRoute
//data class AdministrarVehiculoScreenRoute(val name: String? = null)

@Serializable
object NuevaOrdenDeVentaScreenRoute
//data class NuevaOrdenDeVentaScreenRoute(val name: String? = null)


/**   D I A L O G S   */
@Serializable
object AperturaCajaCantPorDenominacionDialogRoute
//data class AperturaCajaCantPorDenominacionDialogRoute( val x: XXX )

@Serializable
object AperturaCajaConfirmacionDialogRoute
//data class AperturaCajaConfirmacionDialogRoute( val x: XXX )

@Serializable
object CierreCajaCantPorDenominacionDialogRoute
//data class CierreCajaCantPorDenominacionDialogRoute( val x: XXX )

@Serializable
object CierreCajaConfirmacionDialogRoute
//data class CierreCajaConfirmacionDialogRoute( val x: XXX )

@Serializable
object AgregarMonedaDialogRoute
//data class AgregarMonedaDialogRoute( val x: XXX )

@Serializable
object AgregarClienteDialogRoute
//data class AgregarClienteDialogRoute( val x: XXX )

@Serializable
object AgregarOperarioDialogRoute
//data class AgregarOperarioDialogRoute( val x: XXX )

@Serializable
object AgregarNombreServicioDialogRoute
//data class AgregarNombreServicioDialogRoute( val x: XXX )

@Serializable
object AgregarServicioYPrecioDialogRoute
//data class AgregarServicioYPrecioDialogRoute( val x: XXX )

@Serializable
object AgregarClasificacionVehiculoDialogRoute
//data class AgregarClasificacionVehiculoDialogRoute( val x: XXX )

@Serializable
object AgregarVehiculoDialogRoute
//data class AgregarVehiculoDialogRoute( val x: XXX )

@Serializable
object BuscarClienteDialogRoute
//data class BuscarClienteDialogRoute( val x: XXX )

@Serializable
object NuevaOrdenDeVentaDialogRoute
//data class BuscarVehiculoDialogRoute( val x: XXX )

@Serializable
object NuevoServicioDialogRoute
//data class NuevoServicioDialogRoute( val x: XXX )



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

    /**   V I E W   M O D E L S   **/
    mUnidadMonetariaViewModel: UnidadMonetariaViewModel =
        viewModel(factory = UnidadMonetariaViewModel.Factory),

    mTipoMonedaViewModel: TipoMonedaViewModel =
        viewModel(factory = TipoMonedaViewModel.Factory),

    mMonedaViewModel: MonedaViewModel =
        viewModel(factory = MonedaViewModel.Factory),

    mDenominacionMonedaViewModel: DenominacionMonedaViewModel =
        viewModel(factory = DenominacionMonedaViewModel.Factory),

    mCajaViewModel: CajaViewModel =
        viewModel(factory = CajaViewModel.Factory),

    mClienteViewModel: ClienteViewModel =
        viewModel(factory = ClienteViewModel.Factory),

    mOperarioViewModel: OperarioViewModel =
        viewModel(factory = OperarioViewModel.Factory),

    mServicioViewModel: ServicioViewModel =
        viewModel(factory = ServicioViewModel.Factory),

    mServicioYPrecioViewModel: ServicioYPrecioViewModel =
        viewModel(factory = ServicioYPrecioViewModel.Factory),

    mPrecioViewModel: PrecioViewModel =
        viewModel(factory = PrecioViewModel.Factory),

    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel =
        viewModel(factory = ClasificacionDelVehiculoViewModel.Factory),

    mVehiculoViewModel: VehiculoViewModel =
        viewModel(factory = VehiculoViewModel.Factory),

    mOrdenDeVentaViewModel: OrdenDeVentaViewModel =
        viewModel(factory = OrdenDeVentaViewModel.Factory),

    mOrdenDePagoNominaViewModel: OrdenDePagoNominaViewModel =
            viewModel(factory = OrdenDePagoNominaViewModel.Factory),

    mDetalleOrdenServicioViewModel: DetalleOrdenServicioViewModel =
            viewModel(factory = DetalleOrdenServicioViewModel.Factory),

    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = InicioScreenRoute,
    ) {

        composable<InicioScreenRoute> {
            InicioScreen(
                onNavigateToAperturaCajaCantidadesPorDenominacionDialog = {
                    navController.navigate( route = AperturaCajaCantPorDenominacionDialogRoute )
                },

                onNavigateToCierreCajaCantidadesPorDenominacionDialog = {
                    navController.navigate( route = CierreCajaCantPorDenominacionDialogRoute )
                },

                onNavigateToAdministrarMonedaScreen = {
                    navController.navigate( route = AdministrarMonedaScreenRoute )
                },

                onNavigateToAdministrarServiciosScreen = {
                    navController.navigate( route = AdministrarServiciosScreenRoute )
                },

                onNavigateToAdministrarEmpleadosScreen = {
                    navController.navigate( route = AdministrarEmpleadosScreenRoute )
                },

                onNavigateToAdministrarCategoriasScreen = {
                    navController.navigate( route = AdministrarClasificacionVehiculoScreenRoute )
                },

                onNavigateToAdministrarClientesScreen = {
                    navController.navigate( route = AdministrarClientesScreenRoute )
                },

                onNavigateToAdministrarVehiculosScreen = {
                    navController.navigate( route = AdministrarVehiculosScreenRoute )
                },

                onNavigateToNuevaOrdenVenta = {
                    navController.navigate( route = NuevaOrdenDeVentaDialogRoute )
                },

                mCajaViewModel = mCajaViewModel,
                mOrdenDeVentaViewModel = mOrdenDeVentaViewModel,
            )
        }

        composable<AdministrarMonedaScreenRoute>{
            AdministrarMonedaScreen(
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_moneda = {
                    navController.navigate(route = AgregarMonedaDialogRoute) },
                mMVM = mMonedaViewModel
            )
        }

        composable<AdministrarServiciosScreenRoute>{
            AdministrarServiciosScreen(
                mServicioYPrecioViewModel = mServicioYPrecioViewModel,
                mServicioViewModel =  mServicioViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_servicio_y_precio = {
                    navController.navigate(route = AgregarServicioYPrecioDialogRoute)
                },
                onClick_crear_servicio = {
                    navController.navigate(route = AgregarNombreServicioDialogRoute)
                }
            )
        }

        composable<AdministrarEmpleadosScreenRoute>{
            AdministrarEmpleadosScreen(
                mOperarioViewModel = mOperarioViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_empleado = {
                    navController.navigate(route = AgregarOperarioDialogRoute)
                }
            )
        }

        composable<AdministrarClientesScreenRoute>{
            AdministrarClientesScreen(
                mClienteViewModel =  mClienteViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_cliente = {
                    navController.navigate(route = AgregarClienteDialogRoute)
                }
            )
        }

        composable<AdministrarClasificacionVehiculoScreenRoute>{
            AdministrarCategoriasScreen(
                mClasificacionDelVehiculoViewModel = mClasificacionDelVehiculoViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_categoria = {
                    navController.navigate(route = AgregarClasificacionVehiculoDialogRoute)
                }
            )
        }

        composable<AdministrarVehiculosScreenRoute>{
            AdministrarVehiculosScreen(
                mClasificacionDelVehiculoViewModel = mClasificacionDelVehiculoViewModel,
                mVehiculoViewModel = mVehiculoViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_vehiculo = {
                    navController.navigate(route = AgregarVehiculoDialogRoute)
                }
            )
        }

        composable<NuevaOrdenDeVentaScreenRoute>{
            NuevaOrdenDeVentaScreen(
                mClienteViewModel = mClienteViewModel,
                mVehiculoViewModel = mVehiculoViewModel,
                mClasificacionDelVehiculoViewModel = mClasificacionDelVehiculoViewModel,
                mOperarioViewModel = mOperarioViewModel,
                mDetalleOrdenServicioViewModel =mDetalleOrdenServicioViewModel,
                mServicioYPrecioViewModel = mServicioYPrecioViewModel,
                mOrdenDeVentaViewModel = mOrdenDeVentaViewModel,
                onClick_navigate_back = { navController.navigateUp( ) },
                onNavigateToAgregarServicioDialog = {
                    navController.navigate( route = NuevoServicioDialogRoute )
                }
            )
        }



        /**   D I A L O S   **/
        dialog<AperturaCajaCantPorDenominacionDialogRoute> {
            AperturaCajaCantidadesPorDenominacionDialog(
                onNavigateToAperturaCajaConfirmacionDialog = {
                    navController.navigateUp( )
                    navController.navigate( route = AperturaCajaConfirmacionDialogRoute )
                },
                onDismissFromAperturaCajaCantidadesPorDenominacionDialog = {
                    navController.navigateUp( )
                },
                mCajaViewModel = mCajaViewModel,
                mMonedaViewModel = mMonedaViewModel,
            )
        }
        
        dialog<AperturaCajaConfirmacionDialogRoute> {
            AperturaCajaConfirmacionDialog(

                onNavigateToInicioScreen = {
                    navController.navigateUp( )
                    //navController.navigate( route = AperturaCajaConfirmacionRoute )
                },

                onDismissFromAperturaCajaConfirmacionDialog = {
                    //navController.navigateUp( )
                    //navController.navigate( route = AperturaCajaCantPorDenominacionDialogRoute )
                    navController.navigateUp( )
                },
                mCajaViewModel = mCajaViewModel
            )
        }

        dialog<CierreCajaCantPorDenominacionDialogRoute> {
            CierreCajaCantidadesPorDenominacionDialog(

                onNavigateToCerrarCajaConfirmacionDialog = {
                    navController.navigateUp( )
                    navController.navigate( route = CierreCajaConfirmacionDialogRoute )
                },
                onDismissFromCerrarCajaCantidadesPorDenominacionDialog = {
                    navController.navigateUp( )
                },
                mCajaViewModel = mCajaViewModel,
                mMonedaViewModel = mMonedaViewModel,
            )
        }

        dialog<CierreCajaConfirmacionDialogRoute> {
            CierreCajaConfirmacionDialog(

                onNavigateToInicioScreen = {
                    navController.navigateUp( )
                    //navController.navigate( route = CierreCajaConfirmacionRoute )
                },

                onDismissFromCierreCajaConfirmacionDialog = {
                    //navController.navigateUp( )
                    //navController.navigate( route = CierreCajaCantPorDenominacionDialogRoute )
                    navController.navigateUp( )
                },
                mCajaViewModel = mCajaViewModel
            )
        }

        dialog<AgregarMonedaDialogRoute> {
            AgregarMonedaDialog(
                onDismissFromAgregarMonedaDialog = { navController.navigateUp( ) },
                mUMVM = mUnidadMonetariaViewModel,
                mTMVM = mTipoMonedaViewModel,
                mMVM = mMonedaViewModel,
                mDMVM = mDenominacionMonedaViewModel,
            )
        }

        dialog<AgregarClienteDialogRoute> {
            AgregarClienteDialog(
                onDismissFromAgregarClienteDialog = { navController.navigateUp( ) },
                mClienteViewModel = mClienteViewModel,
            )
        }

        dialog<AgregarOperarioDialogRoute> {
            AgregarOperarioDialog(
                onDismissFromAgregarClienteDialog = { navController.navigateUp( ) },
                mOperarioViewModel = mOperarioViewModel,
            )
        }

        dialog<AgregarNombreServicioDialogRoute> {
            AgregarNombreDelServicioDialog(
                onDismissFromAgregarNombreServicioDialog = { navController.navigateUp( ) },
                mServicioViewModel = mServicioViewModel,
            )
        }

        dialog<AgregarServicioYPrecioDialogRoute> {
            AgregarServicioYPrecioDialog(
                mServicioViewModel = mServicioViewModel,
                mPrecioViewModel = mPrecioViewModel,
                mServicioYPrecioViewModel = mServicioYPrecioViewModel,
                mUnidadMonetariaViewModel = mUnidadMonetariaViewModel,
                onDismissFromAgregarServicioYPrecioDialog = { navController.navigateUp( ) },
            )
        }

        dialog<AgregarClasificacionVehiculoDialogRoute> {
            AgregarClasificacionVehiculoDialog(
                mClasificacionDelVehiculoViewModel = mClasificacionDelVehiculoViewModel,
                onDismissFromAgregarClasificacionVehiculoDialog= { navController.navigateUp( ) },
            )
        }

        dialog<AgregarVehiculoDialogRoute> {
            AgregarVehiculoDialog(
                mClasificacionDelVehiculoViewModel = mClasificacionDelVehiculoViewModel,
                mVehiculoViewModel = mVehiculoViewModel,
                onDismissFromAgregarVehiculoDialog= { navController.navigateUp( ) },
            )
        }

        dialog<BuscarClienteDialogRoute> {
            BuscarClienteDialog(
                mClienteViewModel = mClienteViewModel,
                onNavigateToVincularVehiculoYClienteDialog = { navController.navigate(route = NuevaOrdenDeVentaDialogRoute ) },
                onDismissFromBuscarClienteDialog = { navController.navigateUp( ) },
            )
        }

        dialog<NuevaOrdenDeVentaDialogRoute> {
            NuevaOrdenDeVentaDialog(
                mClienteViewModel = mClienteViewModel,
                mVehiculoViewModel = mVehiculoViewModel,
                onDismissFromNuevaOrdenDeVentaDialog = { navController.navigateUp( ) },
                onNavigateToNuevaOrdenDeVentaScreen = {
                    navController.navigate( route = NuevaOrdenDeVentaScreenRoute )
                }
            )
        }

        dialog<NuevoServicioDialogRoute> {
            AgregarServicioAOrdenDeVentaDialog(
                mOrdenDeVentaViewModel = mOrdenDeVentaViewModel,
                mOrdenDePagoNominaViewModel = mOrdenDePagoNominaViewModel,
                mServicioYPrecioViewModel = mServicioYPrecioViewModel,
                mOperarioViewModel = mOperarioViewModel,
                onDismissFromAgregarServicioAOrdenDeVentaDialog = {
                    navController.navigateUp( )
                }
            )
        }
    }
}