package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDePagoNominaViewModel
import samirqb.carwashmanager.app.viewmodels.OrdenDeVentaViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun AgregarServicioAOrdenDeVentaDialog(
    mOrdenDeVentaViewModel: OrdenDeVentaViewModel,
    mOrdenDePagoNominaViewModel: OrdenDePagoNominaViewModel,
    mServicioViewModel: ServicioViewModel,
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mOperarioViewModel: OperarioViewModel,
    onDismissFromAgregarServicioAOrdenDeVentaDialog: ()-> Unit,
) {

    val uiState_ServicioViewModel by mServicioViewModel.uiState.collectAsState()
    val uiState_OrdenDeVentaViewModel by mOrdenDeVentaViewModel.uiState.collectAsState()
    val uiState_ServicioYPrecioUiState by mServicioYPrecioViewModel.uiState.collectAsState()

    mServicioYPrecioViewModel.listarTodosLosServiciosYPreciosUC()
    mServicioViewModel.listarTodosLosServiciosUC()

    var servicio_y_precio_seleccionado_flag by rememberSaveable { mutableStateOf(false)}
    var operario_seleccionado_flag by rememberSaveable { mutableStateOf(false)}

    var listar_todos_los_servicios_y_precios_activos = uiState_ServicioYPrecioUiState.listar_todos_los_servicios_y_precios_activos
    var listar_todos_los_servicios = uiState_ServicioViewModel.todos_los_servicios.sortedBy {
        it.id_servicio_pk
    }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_details_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_servicio,
        content_dialg_body = {

            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                //.size(432.dp),
            ) {

                //VLayout3P(
                VLayout2P(
                    modifier = Modifier.padding(all = 9.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 19.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    /** ********* DATOS DEL CLIENTE *************** */
                    content1 = {

                        var id_servicio_y_precio_seleccionado by rememberSaveable { mutableIntStateOf(0) }
                        var servicio_y_precio_seleccionado by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }

                        if (id_servicio_y_precio_seleccionado >= 0) {
                            servicio_y_precio_seleccionado_flag = true
                        }

                        /*
                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(113.dp),
                            //.weight(1f),
                            shape = RoundedCornerShape(13),
                            color = MaterialTheme.colorScheme.onSecondary,
                        ) {}
                        */

                        sSurface {
                            if (servicio_y_precio_seleccionado.isBlank() or servicio_y_precio_seleccionado.isEmpty()) {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                expander = true
                                            }
                                        },
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = R.string.txt_body_elige_un_servicio_y_precio,
                                )
                            } else {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                expander = true
                                            }
                                        },
                                    //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = servicio_y_precio_seleccionado,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = { expander = false },
                                modifier = Modifier.size(
                                    width = 245.dp,
                                    height = Dp.Unspecified
                                )
                            ) {
                                listar_todos_los_servicios_y_precios_activos.forEachIndexed { index, item ->

                                    var id_nombre_del_servicio = listar_todos_los_servicios.binarySearch(item.id_servicio_fk) {
                                        it.id_servicio_pk
                                    }
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${id_nombre_del_servicio } - ${item.precio_fk} ${item.codigo_iso_4217_fk}") },
                                        onClick = {
                                            id_servicio_y_precio_seleccionado =
                                                item.id_servicio_fk
                                            servicio_y_precio_seleccionado =
                                                "${id_nombre_del_servicio}"
                                            expander = false
                                        },
                                    )
                                }
                            }
                        }

                    },

                    /** ********** CAMPO PARA BUSCAR MATRICULA DEL VEHICULO ************* */
                    content2 = {
                        /*
                        var value by rememberSaveable { mutableStateOf("") }

                        if (value.length >= 1) {
                            min_char_to_enabled_btn2_from_vehiculo = true
                        } else {
                            min_char_to_enabled_btn2_from_vehiculo = false
                        }

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(113.dp),
                            //.weight(1f),
                            shape = RoundedCornerShape(13),
                            color = MaterialTheme.colorScheme.onSecondary,
                        ) {


                        }
                        */
                    },
                )
            }

        },
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {},
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {},
        enabled_btn1 = true,
        enabled_btn2 = true,
        //header
        modifier_content_header = Modifier
            .fillMaxWidth()
            .size(150.dp),

        //botones accion
        modifier_content_action_buttons = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}