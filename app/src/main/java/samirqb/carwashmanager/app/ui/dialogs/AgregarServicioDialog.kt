package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout1P
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.lib.helpers.ValidarEntradasRegex

@Composable
fun AgregarServicioDialog(
    mServicioViewModel: ServicioViewModel,
    onDismissFromAgregarClienteDialog: () -> Unit,
){

    val mValidarEntradasRegex = ValidarEntradasRegex()
    var nombre_servicio_value by rememberSaveable { mutableStateOf("") }


    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_design_services_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_nombre_servicio,
        content_dialg_body = {

            sSurface(
                color = MaterialTheme.colorScheme.secondaryContainer
                /*
                modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),
                */
            ) {
                VLayout1P(
                    content1 = {

                        var value by rememberSaveable { mutableStateOf("") }

                        xOutlinedTextField_CHAR(
                            value = value,
                            onValueChange = {

                                if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                    value = it.uppercase()
                                    nombre_servicio_value = it.uppercase()
                                } else if(it.isEmpty()) {
                                    value = it
                                    nombre_servicio_value = it
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
                )
            }

        },
        boton_txt_1 = R.string.txt_label_agregar,
        on_click_boton_1 = {},
        boton_txt_2 = R.string.txt_label_cancelar,
        on_click_boton_2 = {}
    )

}