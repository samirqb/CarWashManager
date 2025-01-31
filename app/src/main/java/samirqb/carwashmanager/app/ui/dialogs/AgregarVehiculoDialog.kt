package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_CHAR
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.ClasificacionDelVehiculoViewModel
import samirqb.carwashmanager.app.viewmodels.VehiculoViewModel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.vehiculos.entities.VehiculoEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarVehiculoDialog(
    mClasificacionDelVehiculoViewModel: ClasificacionDelVehiculoViewModel,
    mVehiculoViewModel: VehiculoViewModel,
    onDismissFromAgregarVehiculoDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    mClasificacionDelVehiculoViewModel.listarTodasLasClasificacionesDeVehiculoUserCase()
    mVehiculoViewModel.actualizarFechaYHora()

    val uiState_ClasificacionDelVehiculo by mClasificacionDelVehiculoViewModel.uiState.collectAsState()
    val uiState_Vehiculo by mVehiculoViewModel.uiState.collectAsState()

    var lista_categorias_vehiculo = uiState_ClasificacionDelVehiculo.listar_todas_las_clasificaciones_de_vehiculo

    var enabled_btn1 by rememberSaveable { mutableStateOf(true) }
    var enabled_btn2 by rememberSaveable { mutableStateOf(false) }
    var categoria_seleccionada by rememberSaveable { mutableStateOf(0) }
    var matricula_vehiculo_value by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_transportation_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_moneda,
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

                    content2 = {
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
                                        matricula_vehiculo_value = it.uppercase()
                                    } else {
                                        value = ""
                                        matricula_vehiculo_value = ""
                                    }
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
            onDismissFromAgregarVehiculoDialog()
        },

        enabled_btn2 = enabled_btn2,
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {

            mVehiculoViewModel.agregarNuevoVehiculo(
                VehiculoEntity(
                    matricula_pk = matricula_vehiculo_value,
                    clase_id_fk = categoria_seleccionada,
                    fecha_hora_creacion = uiState_Vehiculo.fecha_y_hora,
                )
            )
            onDismissFromAgregarVehiculoDialog()
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