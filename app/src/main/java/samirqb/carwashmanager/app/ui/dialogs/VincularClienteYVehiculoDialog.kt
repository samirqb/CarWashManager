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
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun VincularClienteYVehiculoDialog(
    mClienteViewModel: ClienteViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    onDismissFromVincularClienteYVehiculoDialog: () -> Unit,
    //onNavigateToVincularVehiculoDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    val uiState_ClienteViemodel by mClienteViewModel.uiState.collectAsState()
    val uiState_VehiculoViemodel by mClienteViewModel.uiState.collectAsState()

    val resultado_busqueda_cliente = uiState_ClienteViemodel.resultado_busqueda_cliente

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var enabled_btn2 by rememberSaveable { mutableStateOf(false) }
    var matricala_value by rememberSaveable { mutableStateOf("") }
    var busqueda_realizada by rememberSaveable { mutableStateOf( false) }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_add_link_24,
        header_text_titulo_id = R.string.txt_titulo_enlazar_vehiculo,
        content_dialg_body = {
            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer
                /*modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),*/
            ) {
                VLayout3P(
                    modifier = Modifier.padding(all = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 13.dp),

                    /** DATOS DEL CLIENTE */
                    content1 = {
                        VLayout3P(
                            content1 = {
                                xTextTitle(text = resultado_busqueda_cliente!!.nombre_apellidos)
                            },
                            content2 = {
                                HLayout2P(
                                    content1 = {
                                        sIcon(image_vector_id = R.drawable.rounded_id_card_24)
                                    },
                                    content2 = {
                                        xTextBody(text = resultado_busqueda_cliente!!.identificacion_pk)
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
                    },

                    /** CAMPO PARA BUSCAR MATRICULA DEL VEHICULO */
                    content2 = {
                        sSurface() {
                            var value by rememberSaveable { mutableStateOf("") }

                            if (value.length >= 1) {
                                enabled_btn2 = true
                            } else {
                                enabled_btn2 = false
                            }

                            //denominacion_value
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
                            )
                        }
                    },

                    content3 = {
                        sSurface {
                            if (busqueda_realizada && resultado_busqueda_cliente == null) {
                                xTextBody(
                                    text = stringResource(R.string.txt_body_registro_no_existe),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }

                            if (busqueda_realizada && resultado_busqueda_cliente != null){
                                xTextBody(
                                    text = "OK"
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
            onDismissFromVincularClienteYVehiculoDialog()
        },

        enabled_btn2 = enabled_btn2,
        boton_txt_2 = R.string.txt_label_buscar,
        on_click_boton_2 = {

            mVehiculoViewModel.buscarVehiculoPorMatriculaUseCase(matricala_value)

            busqueda_realizada = true

            if (resultado_busqueda_cliente != null) {
                onDismissFromVincularClienteYVehiculoDialog()
                //onNavigateToVincularVehiculoDialog()
            }

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