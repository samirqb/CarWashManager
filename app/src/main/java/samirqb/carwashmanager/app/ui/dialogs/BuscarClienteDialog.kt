package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.personas.entities.ClienteEntity

@Composable
fun BuscarClienteDialog(
    mClienteViewModel: ClienteViewModel,
    onNavigateToVincularVehiculoYClienteDialog: () -> Unit,
    onDismissFromBuscarClienteDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var enabled_btn2 by rememberSaveable { mutableStateOf(false) }
    var cliente_id_value by rememberSaveable { mutableStateOf("") }
    var busqueda_realizada by rememberSaveable { mutableStateOf( false) }

    val uiState by mClienteViewModel.uiState.collectAsState()

    var resultado_busqueda_cliente = uiState.resultado_busqueda_cliente
    //var resultado_busqueda_cliente by remember {  mutableStateOf(null) }


    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_person_search_24,
        header_text_titulo_id = R.string.txt_titulo_buscar_cliente,
        content_dialg_body = {
            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer
                /*modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),*/
            ) {
                VLayout2P(
                    modifier = Modifier.padding(all = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 13.dp),

                    /*
                    content1 = {

                        var categoria_vehiculo by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }

                        sSurface {
                            if (categoria_vehiculo.isBlank() or categoria_vehiculo.isEmpty()) {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = R.string.txt_body_elige_una_unidad_monetaria,
                                )
                            } else {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = categoria_vehiculo,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = { expander = false },
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ) {
                                lista_categorias_vehiculo.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${item.clase_id_pk} - ${item.descripcion}") },
                                        onClick = {
                                            categoria_seleccionada = item.clase_id_pk
                                            categoria_vehiculo =
                                                "${item.clase_id_pk} - ${item.descripcion}"
                                            expander = false
                                        }
                                    )

                                }
                            }
                        }

                    },
                    */

                    content1 = {
                        sSurface() {
                            var value by rememberSaveable { mutableStateOf("") }

                            if(value.length >= 1){
                                enabled_btn2 = true
                            } else {
                                enabled_btn2 = false
                            }

                            //denominacion_value
                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if(mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        cliente_id_value = it.uppercase()
                                    } else {
                                        value = ""
                                        cliente_id_value = ""
                                    }
                                },
                            )
                        }
                    },

                    content2 = {

                        if (busqueda_realizada && resultado_busqueda_cliente != null) {
                            /*
                            onDismissFromBuscarClienteDialog()
                            onNavigateToVincularVehiculoYClienteDialog()
                            */

                            VLayout3P(
                                content1 = {
                                    xTextTitle(text = resultado_busqueda_cliente.nombre_apellidos)
                                },
                                content2 = {
                                    HLayout2P(
                                        content1 = {
                                            sIcon(image_vector_id = R.drawable.rounded_id_card_24)
                                        },
                                        content2 = {
                                            xTextBody(text = resultado_busqueda_cliente.identificacion_pk)
                                        },
                                    )
                                },
                                content3 = {
                                    HLayout2P(
                                        content1 = {
                                            sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24)
                                        },
                                        content2 = {
                                            xTextBody(text = resultado_busqueda_cliente!!.telefono)
                                        },
                                    )
                                },
                            )
                        }

                        if (busqueda_realizada && resultado_busqueda_cliente == null){
                            sSurface {
                                xTextBody(
                                    text = stringResource(R.string.txt_body_registro_no_existe),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    },
                )
            }
        },

        enabled_btn1 = enabled_btn1,
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {
            onDismissFromBuscarClienteDialog()
        },

        enabled_btn2 = enabled_btn2,
        boton_txt_2 = R.string.txt_label_buscar,
        on_click_boton_2 = {

            mClienteViewModel.buscarClientePorIdUseCase( cliente_id_value )

            busqueda_realizada = true

        },
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),
        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
        //modifier_content3: Modifier = Modifier
    )

}