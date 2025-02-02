package samirqb.carwashmanager.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.layouts.sLazyColumn
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.components.custom.widgets.EstadoCajaWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.ServiciosActivosWidget
import samirqb.carwashmanager.app.ui.templates.modalbottomsheets.tModalBottomSheet
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM1
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tScaffoldM2
import samirqb.carwashmanager.app.viewmodels.CajaViewModel
import samirqb.lib.caja.entidades.CierreCajaEntity

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    onNavigateToAperturaCajaCantidadesPorDenominacionDialog: () -> Unit,
    onNavigateToCierreCajaCantidadesPorDenominacionDialog: () -> Unit,
    onNavigateToAdministrarMonedaScreen: () -> Unit,
    onNavigateToAdministrarServiciosScreen: () -> Unit,
    onNavigateToAdministrarEmpleadosScreen: () -> Unit,
    onNavigateToAdministrarCategoriasScreen: () -> Unit,
    onNavigateToAdministrarClientesScreen: () -> Unit,
    onNavigateToAdministrarVehiculosScreen: () -> Unit,
    onNavigateToNuevaOrdenVenta: () -> Unit,
    mCajaViewModel: CajaViewModel,
) {

    //val uiState_CVM by mCajaViewModel.uiState.collectAsState()
    val uiState_AperturaCajaVM by mCajaViewModel.uiState_AperturaCaja.collectAsState()

    var apertura_de_caja by rememberSaveable { mutableStateOf(false) }
    var lista_trabajadores = listOf("Arturo", "Guillermo", "Maicol", "Brayan", "Duran", "Diego", "Nijak")
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    //mCajaViewModel.obtenerUltimaAperturaCaja()
    apertura_de_caja = uiState_AperturaCajaVM.ultimaAperturaCaja?.apertura_activa ?: false


    if (apertura_de_caja) {
    //if (false) {
        /*
        sSurface(
            modifier = Modifier.fillMaxSize()
        ) {
            VLayout1P(
                modifier = Modifier.fillMaxSize(),
                content1 = {
                    xTextTitle(text = "APERTURA OK")
                    //xTextTitle(text = stringResource(id = R.string.informacion_no_disponible))
                }
            )
        }
        */

        tScaffoldM2(
            modifier = Modifier.fillMaxSize(),
            top_app_bar_title = R.string.txt_headline_inicio,
            top_app_bar_subtitle = R.string.txt_label_app_subtitulo_estatus_abierto,
            top_app_bar_action_button_icon_1 = R.drawable.rounded_more_vert_24,
            top_app_bar_action_button_onClick1 = {},
            onClick_dropdownmenuitem_admin_moneda = {onNavigateToAdministrarMonedaScreen()},
            onClick_dropdownmenuitem_admin_servicios = { onNavigateToAdministrarServiciosScreen() },
            onClick_dropdownmenuitem_admin_empleados = { onNavigateToAdministrarEmpleadosScreen() },
            onClick_dropdownmenuitem_admin_categorias = { onNavigateToAdministrarCategoriasScreen() },
            onClick_dropdownmenuitem_admin_clientes = { onNavigateToAdministrarClientesScreen() },
            onClick_dropdownmenuitem_admin_vehiculos = { onNavigateToAdministrarVehiculosScreen() },
            top_app_bar_action_button_icon_2 = R.drawable.baseline_output_24,
            top_app_bar_action_button_onClick2 = {
                onNavigateToCierreCajaCantidadesPorDenominacionDialog()
            },
            content1 = {
                EstadoCajaWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.size(500.dp)
                    ,
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

            floatingActionButton_onClick = {
                onNavigateToNuevaOrdenVenta()
            },

            floatingActionButton_image_vector_id = R.drawable.rounded_local_car_wash_24
        )


    } else {

        tScaffoldM1(
            //modifier = Modifier.fillMaxSize(),
            top_app_bar_title = R.string.app_name,
            top_app_bar_subtitle = R.string.txt_label_app_subtitulo_estatus_cerrado,
            content1 = {

                if(uiState_AperturaCajaVM.lista_aperturas_caja.isEmpty()){
                    sSurface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        VLayout1P(
                            modifier = Modifier.fillMaxSize(),
                            content1 = {
                                //xTextTitle(text = "No existen monedas agregadas")
                                xTextTitle(text = stringResource(id = R.string.informacion_no_disponible))
                            }
                        )
                    }
                } else {
                    sLazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(space = 1.dp)
                    ) {

                        mCajaViewModel.listarTodasLasAperturas()

                        itemsIndexed(uiState_AperturaCajaVM.lista_aperturas_caja){ index, item ->
                            sSurface(
                                modifier = Modifier.fillMaxWidth().size(87.dp)
                            ) {
                                VLayout3P(
                                    content1 = {
                                        xTextLabel(text = "Apertura numero: ${item.id_apertura_caja_pk}")
                                    },
                                    content2 = {
                                        xTextBody(text = "${item.fecha_hora_creacion}")
                                    },
                                    content3 = {
                                        xTextHeadLine(text = "$${item.total_dinero_apertura}")
                                    },
                                )
                            }
                            HorizontalDivider()
                        }

                    }
                }
            },

            top_app_bar_action_button_icon_1 = R.drawable.rounded_more_vert_24,
            top_app_bar_action_button_onClick1 = { },
            onClick_dropdownmenuitem_admin_moneda = {onNavigateToAdministrarMonedaScreen()},
            onClick_dropdownmenuitem_admin_servicios = { onNavigateToAdministrarServiciosScreen() },
            onClick_dropdownmenuitem_admin_empleados = { onNavigateToAdministrarEmpleadosScreen() },
            onClick_dropdownmenuitem_admin_categorias = { onNavigateToAdministrarCategoriasScreen() },
            onClick_dropdownmenuitem_admin_clientes = { onNavigateToAdministrarClientesScreen() },
            onClick_dropdownmenuitem_admin_vehiculos = { onNavigateToAdministrarVehiculosScreen() },
            top_app_bar_action_button_icon_2 = R.drawable.rounded_input_24,
            top_app_bar_action_button_onClick2 = {  onNavigateToAperturaCajaCantidadesPorDenominacionDialog() },
        )
    }
}
