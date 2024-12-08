package samirqb.carwashmanager.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.templates.modalbottomsheets.tModalBottomSheet
import samirqb.carwashmanager.app.ui.components.custom.widgets.EstadoCajaWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.ServiciosActivosWidget
import samirqb.carwashmanager.app.ui.templates.dropdowns.tTopAppBarMenuM1
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM1
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM2

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    onNavigateToAperturaCajaCantidadesPorDenominacionDialog: () -> Unit,
    onNavigateToAdministrarMonedaScreen: () -> Unit,
    onNavigateToAdministrarServiciosScreen: () -> Unit,
    onNavigateToAdministrarEmpleadosScreen: () -> Unit,
    onNavigateToAdministrarCategoriasScreen: () -> Unit,
    onNavigateToAdministrarClientesScreen: () -> Unit,
) {

    var apertura_de_caja by rememberSaveable { mutableStateOf(false) }
    var lista_trabajadores = listOf("Arturo", "Guillermo", "Maicol", "Brayan", "Duran", "Diego", "Nijak")
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    if (apertura_de_caja) {

        tScaffoldM2(
            top_app_bar_title = R.string.txt_headline_inicio,
            top_app_bar_subtitle = R.string.txt_label_app_subtitulo_estatus_abierto,
            top_app_bar_action_button_icon_1 = R.drawable.rounded_more_vert_24,
            top_app_bar_action_button_onClick1 = {},
            onClick_dropdownmenuitem_admin_moneda = {onNavigateToAdministrarMonedaScreen()},
            onClick_dropdownmenuitem_admin_servicios = { onNavigateToAdministrarServiciosScreen() },
            onClick_dropdownmenuitem_admin_empleados = { onNavigateToAdministrarEmpleadosScreen() },
            onClick_dropdownmenuitem_admin_categorias = { onNavigateToAdministrarCategoriasScreen() },
            onClick_dropdownmenuitem_admin_clientes = { onNavigateToAdministrarClientesScreen() },
            top_app_bar_action_button_icon_2 = R.drawable.baseline_output_24,
            top_app_bar_action_button_onClick2 = {},
            content1 = {
                EstadoCajaWidget(
                    txt_body_fecha = "2024-11-19",
                    txt_body_hora = "10:27",
                    txt_body_base_apertura = "33900",
                    txt_body_acumulado_ventas = "89700",
                    btn_ver_nomina_onClick = { showBottomSheet = true },
                )
            },

            content2 = {
                ServiciosActivosWidget(
                    lista_servicios_activos = listOf("serv1", "serv2", "serv3", "serv4", "serv5")
                )
            },

            content3 = {
                if (showBottomSheet) {
                    tModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        list_content = lista_trabajadores,
                        titulo_ModalBottomSheet = R.string.txt_titulo_nomina_dia
                    )
                }
            },

            floatingActionButton_onClick = {},

            floatingActionButton_image_vector_id = R.drawable.rounded_local_car_wash_24
        )

    } else {

        tScaffoldM1(
            top_app_bar_title = R.string.app_name,
            top_app_bar_subtitle = R.string.txt_label_app_subtitulo_estatus_cerrado,
            content1 = {},
            top_app_bar_action_button_icon_1 = R.drawable.rounded_more_vert_24,
            top_app_bar_action_button_onClick1 = { },
            onClick_dropdownmenuitem_admin_moneda = {onNavigateToAdministrarMonedaScreen()},
            onClick_dropdownmenuitem_admin_servicios = { onNavigateToAdministrarServiciosScreen() },
            onClick_dropdownmenuitem_admin_empleados = { onNavigateToAdministrarEmpleadosScreen() },
            onClick_dropdownmenuitem_admin_categorias = { onNavigateToAdministrarCategoriasScreen() },
            onClick_dropdownmenuitem_admin_clientes = { onNavigateToAdministrarClientesScreen() },
            top_app_bar_action_button_icon_2 = R.drawable.rounded_input_24,
            top_app_bar_action_button_onClick2 = {  onNavigateToAperturaCajaCantidadesPorDenominacionDialog() },
        )

    }


}
