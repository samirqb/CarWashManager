package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.old.viewcontents.ListaClientesViewContent
import samirqb.carwashmanager.app.ui.components.custom.textfields.config.SeparadorDeMiles
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_NUM
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ClienteViewModel
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun AgregarClienteDialog(
    mClienteViewMpdel: ClienteViewModel,
    onDismissFromAgregarClienteDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()
    var identificacion_value by rememberSaveable { mutableStateOf("") }
    var nombre_y_apellidos_value by rememberSaveable { mutableStateOf("") }
    var telefono_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_group_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_cliente,
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
                    content1 = {

                        sSurface(
                            /*modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )*/
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }
                            //denominacion_value
                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it
                                        identificacion_value = it
                                    } else {
                                        value = value
                                        identificacion_value = identificacion_value
                                    }

                                },
                                visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },
                    content2 = {
                        sSurface(
                            /*modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )*/
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }
                            //denominacion_value
                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it
                                        nombre_y_apellidos_value = it
                                    } else {
                                        value = value
                                        nombre_y_apellidos_value = nombre_y_apellidos_value
                                    }

                                },
                                visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },
                    content3 = {

                        sSurface(
                            /*modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )*/
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }
                            //denominacion_value
                            xOutlinedTextField_NUM(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it
                                        telefono_value = it
                                    } else {
                                        value = value
                                        telefono_value = telefono_value
                                    }

                                },
                                visualTransformation = SeparadorDeMiles()
                            )
                        }

                    },
                )

            }

        },
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {},
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {},

        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),

        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}