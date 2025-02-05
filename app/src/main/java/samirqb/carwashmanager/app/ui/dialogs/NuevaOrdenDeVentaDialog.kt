package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun NuevaOrdenDeVentaDialog(
    mClienteViewModel: ClienteViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    onDismissFromNuevaOrdenDeVentaDialog: () -> Unit,
    onNavigateToNuevaOrdenDeVentaScreen: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var min_char_to_enabled_btn2_from_cliente by rememberSaveable { mutableStateOf(false) }
    var min_char_to_enabled_btn2_from_vehiculo by rememberSaveable { mutableStateOf(false) }

    var cliente_id_value by rememberSaveable { mutableStateOf("") }

    var matricala_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_order_play_24,
        header_text_titulo_id = R.string.txt_titulo_nueva_orden_servidios,
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

                        var value by rememberSaveable { mutableStateOf("") }

                        if (value.length >= 1) {
                            min_char_to_enabled_btn2_from_cliente = true
                        } else {
                            min_char_to_enabled_btn2_from_cliente = false
                        }

                        sSurface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(113.dp),
                            //.weight(1f),
                            shape = RoundedCornerShape(13),
                            color = MaterialTheme.colorScheme.onSecondary,
                        ) {
                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_ingrese_identificacion_cliente),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {
                                    xOutlinedTextField_CHAR(
                                        value = value,
                                        onValueChange = {

                                            if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                value = it.uppercase()
                                                cliente_id_value = it.uppercase()
                                            } else {
                                                value = ""
                                                cliente_id_value = ""
                                            }
                                        },
                                        label = {
                                            HLayout2P(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    9.dp,
                                                    alignment = Alignment.CenterHorizontally
                                                ),
                                                content1 = { sIcon(image_vector_id = R.drawable.rounded_id_card_24) },
                                                content2 = { xTextLabel(text = stringResource(id = R.string.identificacion)) },
                                            )
                                        },
                                    )
                                },
                            )
                        }

                    },

                    /** ********** CAMPO PARA BUSCAR MATRICULA DEL VEHICULO ************* */
                    content2 = {

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

                            VLayout2P(
                                modifier = Modifier.padding(all = 11.dp),
                                verticalArrangement = Arrangement.spacedBy(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                content1 = {
                                    xTextBody(
                                        text = stringResource(R.string.txt_body_ingrese_matricula_vehiculo),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                content2 = {
                                    xOutlinedTextField_CHAR(
                                        value = value,
                                        onValueChange = {

                                            if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                                value = it.uppercase()
                                                matricala_value = it.uppercase()
                                            } else {
                                                value = ""
                                                matricala_value = ""
                                            }
                                        },

                                        label = {
                                            HLayout2P(
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    9.dp,
                                                    alignment = Alignment.CenterHorizontally
                                                ),
                                                content1 = { sIcon(image_vector_id = R.drawable.rounded_pin_24) },
                                                content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_matricula_vehiculo)) },
                                            )
                                        },
                                    )
                                },
                            )
                        }
                    },
                )
            }
        },

        enabled_btn1 = enabled_btn1,
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {
            onDismissFromNuevaOrdenDeVentaDialog()

            /** SE LIMPIAN LOS RESULTADOS DE BUSQUEDA */
            mClienteViewModel.limpiarResultadoDeBusqueda()
            mVehiculoViewModel.limpiarResultadoDeBusqueda()
        },

        enabled_btn2 = min_char_to_enabled_btn2_from_cliente && min_char_to_enabled_btn2_from_vehiculo,
        boton_txt_2 = R.string.txt_label_siguiente,
        on_click_boton_2 = {

            mClienteViewModel.buscarClientePorIdUseCase(cliente_id_value)
            mVehiculoViewModel.buscarVehiculoPorMatriculaUseCase(matricala_value)

            onNavigateToNuevaOrdenDeVentaScreen()

        },
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
