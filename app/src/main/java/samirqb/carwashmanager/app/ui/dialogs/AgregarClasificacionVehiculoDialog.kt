package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.vehiculos.entities.ClasificacionDelVehiculoEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarClasificacionVehiculoDialog(
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    onDismissFromAgregarClasificacionVehiculoDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    val uiState by mClasificacionDelVehiculoViewModel.uiState.collectAsState()

    var fecha_y_hora = uiState.fecha_y_hora

    mClasificacionDelVehiculoViewModel.actualizarFechaYHora()

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var enabled_btn2 by rememberSaveable { mutableStateOf(false) }
    var nombre_clasificacion_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_car_tag_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_clasificacion_vehiculo,
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
                    modifier = Modifier.padding(all = 13.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 19.dp),

                    content1 = {

                        var value by rememberSaveable { mutableStateOf("") }

                        if(value.length >= 1){
                            enabled_btn2 = true
                        } else {
                            enabled_btn2 = false
                        }

                        xOutlinedTextField_CHAR(
                            value = value,
                            onValueChange = {

                                if (mValidarEntradasRegex.validarAlfanumericos(it)) {
                                    value = it.uppercase()
                                    nombre_clasificacion_value = it.uppercase()
                                } else if(it.isEmpty()) {
                                    value = it
                                    nombre_clasificacion_value = it
                                }
                            },
                            label = {
                                HLayout2P(
                                    horizontalArrangement = Arrangement.spacedBy(9.dp, alignment = Alignment.CenterHorizontally),
                                    content1 = { sIcon(image_vector_id = R.drawable.rounded_car_tag_24) },
                                    content2 = { xTextLabel(text = stringResource(id = R.string.txt_label_nombre_clasification)) },
                                )
                            },
                            //visualTransformation = SeparadorDeMiles()
                        )
                    }
                )
            }

        },

        enabled_btn1 = enabled_btn1,
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = { onDismissFromAgregarClasificacionVehiculoDialog() },

        enabled_btn2 = enabled_btn2,
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {
            mClasificacionDelVehiculoViewModel.actualizarFechaYHora()
            mClasificacionDelVehiculoViewModel.agregarNuevaClasificacionDeVehiculo(
                ClasificacionDelVehiculoEntity(
                    clase_id_pk = 0,
                    descripcion = nombre_clasificacion_value,
                    fecha_hora_creacion = uiState.fecha_y_hora
                )
            )
            onDismissFromAgregarClasificacionVehiculoDialog()
        },

        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),

        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )

}