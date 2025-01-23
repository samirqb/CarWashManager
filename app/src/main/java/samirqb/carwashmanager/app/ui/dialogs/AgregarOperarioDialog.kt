package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_TEL
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.OperarioViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.personas.entities.OperarioEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarOperarioDialog(
    mOperarioViewModel: OperarioViewModel,
    onDismissFromAgregarClienteDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    var identificacion_value by rememberSaveable { mutableStateOf("") }
    var nombre_y_apellidos_value by rememberSaveable { mutableStateOf("") }
    var telefono_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_server_person_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_empleado,
        content_dialg_body = {

            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer
                /*
                modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),
                */
            ) {

                VLayout3P(
                    modifier = Modifier.padding(all = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 19.dp),

                    /**  I D E N T I F I C A C I O N  */
                    content1 = {

                        sSurface(
                            /*
                            modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )
                            */
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }

                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        identificacion_value = it.uppercase()
                                    } else if(it.isEmpty()) {
                                        value = it
                                        identificacion_value = it
                                    }
                                },
                                label = {
                                    HLayout2P(
                                        horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                        content1 = { sIcon(image_vector_id = R.drawable.rounded_id_card_24) },
                                        content2 = { xTextLabel(text = stringResource(id = R.string.identificacion)) },
                                    )
                                },
                                //visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },

                    /**  NOMBRES Y APELLIDOS */
                    content2 = {
                        sSurface(
                            /*
                            modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )
                            */
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }

                            xOutlinedTextField_CHAR(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        nombre_y_apellidos_value = it.uppercase()
                                    } else if(it.isEmpty()) {
                                        value = it
                                        nombre_y_apellidos_value = it
                                    }

                                },
                                label = {
                                    HLayout2P(
                                        horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                        content1 = { sIcon(image_vector_id = R.drawable.rounded_insert_text_24) },
                                        content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_nombre)) },
                                    )
                                },
                                //visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },

                    /** TELEFONO CONTACTO */
                    content3 = {

                        sSurface(
                            /*modifier = Modifier.padding(
                                horizontal = 9.dp,
                                vertical = 15.dp,
                            )*/
                        ) {
                            var value by rememberSaveable { mutableStateOf("") }

                            xOutlinedTextField_TEL(
                                value = value,
                                onValueChange = {

                                    if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                        value = it.uppercase()
                                        telefono_value = it.uppercase()
                                    } else if(it.isEmpty()) {
                                        value = it
                                        telefono_value = it
                                    }

                                },
                                label = {
                                    HLayout2P(
                                        //modifier = Modifier.background( color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(17.dp) ),
                                        horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                        content1 = { sIcon(image_vector_id = R.drawable.rounded_perm_phone_msg_24) },
                                        content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_telefono)) },
                                    )
                                },
                                //visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },
                )
            }
        },

        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {
            onDismissFromAgregarClienteDialog()
        },
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {
            mOperarioViewModel.actualizarFechaYHora()
            mOperarioViewModel.agregarOperariosUC(
                OperarioEntity(
                    identificacion_pk = identificacion_value,
                    nombre_apellido = nombre_y_apellidos_value,
                    telefono = telefono_value,
                    fecha_hora_creacion = mOperarioViewModel.uiState.value.fecha_y_hora,
                )
            )
            onDismissFromAgregarClienteDialog()
        },

        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),

        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}
