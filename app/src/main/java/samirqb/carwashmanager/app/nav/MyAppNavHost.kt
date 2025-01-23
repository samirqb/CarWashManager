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
import samirqb.carwashmanager.app.ui.dialogs.AgregarClienteDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarMonedaDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarOperarioDialog
import samirqb.carwashmanager.app.ui.dialogs.AgregarServicioDialog
import samirqb.carwashmanager.app.ui.dialogs.AperturaCajaCantidadesPorDenominacionDialog
import samirqb.carwashmanager.app.ui.dialogs.AperturaCajaConfirmacionDialog
import samirqb.carwashmanager.app.ui.dialogs.CierreCajaCantidadesPorDenominacionDialog
import samirqb.carwashmanager.app.ui.dialogs.CierreCajaConfirmacionDialog
import samirqb.carwashmanager.app.ui.screens.AdministrarCategoriasScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarClientesScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarEmpleadosScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarMonedaScreen
import samirqb.carwashmanager.app.ui.screens.AdministrarServiciosScreen
import samirqb.carwashmanager.app.ui.screens.InicioScreen
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.DenominacionMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.MonedaViewModel
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.TipoMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.UnidadMonetariaViewModel


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
object AdministrarCategoriasScreenRoute
//data class AdministrarCategoriasScreenRoute(val name: String? = null)


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
object AgregarServicioDialogRoute
//data class AgregarServicioDialogRoute( val x: XXX )



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
                    navController.navigate( route = AdministrarCategoriasScreenRoute )
                },

                onNavigateToAdministrarClientesScreen = {
                    navController.navigate( route = AdministrarClientesScreenRoute )
                },

                mCajaViewModel = mCajaViewModel
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
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_servicio = {
                    //navController.navigate(route = AgregarServicioDialogRoute)
                },
                onClick_crear_servicio = {
                    navController.navigate(route = AgregarServicioDialogRoute)
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

        composable<AdministrarCategoriasScreenRoute>{
            AdministrarCategoriasScreen(
                onClick_navigate_back = { navController.navigateUp( ) },
                onClick_agregar_categoria = {
                    //navController.navigate(route = AgregarServicioDialogRoute)
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

        dialog<AgregarServicioDialogRoute> {
            AgregarServicioDialog(
                onDismissFromAgregarClienteDialog = { navController.navigateUp( ) },
                mServicioViewModel = mServicioViewModel,
            )
        }
    }
}