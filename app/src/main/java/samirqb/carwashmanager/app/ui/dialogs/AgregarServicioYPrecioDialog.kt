package samirqb.carwashmanager.app.ui.dialogs

import android.os.Build
import android.util.Log
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
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.config.SeparadorDeMiles
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField_NUM
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.PrecioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioViewModel
import samirqb.carwashmanager.app.viewmodels.ServicioYPrecioViewModel
import samirqb.carwashmanager.app.viewmodels.UnidadMonetariaViewModel
import samirqb.lib.helpers.ValidarEntradasRegex
import samirqb.lib.ofertas.entities.PrecioEntity
import samirqb.lib.ofertas.entities.ServicioYPrecioEntity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarServicioYPrecioDialog(
    mServicioViewModel: ServicioViewModel,
    mPrecioViewModel: PrecioViewModel,
    mServicioYPrecioViewModel: ServicioYPrecioViewModel,
    mUnidadMonetariaViewModel: UnidadMonetariaViewModel,
    onDismissFromAgregarServicioYPrecioDialog: () -> Unit,
) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    mServicioViewModel.listarTodosLosServiciosUC()
    mUnidadMonetariaViewModel.leerTodo()

    val uiState_ServicioViewModel by mServicioViewModel.uiState.collectAsState()
    val uiState_PrecioViewModel by mPrecioViewModel.uiState.collectAsState()
    val uiState_UnidadMonetariaViewModel by mUnidadMonetariaViewModel.uiState.collectAsState()

    var servicio_seleccionado by rememberSaveable { mutableStateOf(0) }
    var precio_servicio_value by rememberSaveable { mutableStateOf("") }
    var unidad_monetaria_seleccionada by rememberSaveable { mutableStateOf("") }

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_local_car_wash_24 ,
        header_text_titulo_id = R.string.txt_titulo_agregar_servicio,
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

                        var servicio by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }


                        sSurface {
                            if (servicio.isBlank() or servicio.isEmpty()) {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = R.string.txt_body_elige_un_servicio,
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
                                    txt_body = servicio,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = { expander = false },
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ) {
                                uiState_ServicioViewModel.todos_los_servicios.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${item.id_servicio_pk} - ${item.descripcion}") },
                                        onClick = {
                                            servicio_seleccionado = item.id_servicio_pk
                                            servicio =
                                                "${item.id_servicio_pk} - ${item.descripcion}"
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

                            xOutlinedTextField_NUM(
                                value = value,
                                onValueChange = {

                                    if(mValidarEntradasRegex.validarNumerosDecimales(it)) {
                                        value = it
                                        precio_servicio_value = it
                                    } else {
                                        value = ""
                                        precio_servicio_value = ""
                                    }

                                },
                                visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },

                    content3 = {

                        var unidad_monetaria by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }


                        sSurface {
                            if (unidad_monetaria.isBlank() or unidad_monetaria.isEmpty()) {
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
                                    txt_body = unidad_monetaria,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = { expander = false },
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ) {
                                uiState_UnidadMonetariaViewModel.lista_unidades_monetarias.forEachIndexed { index, item ->
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${item.codigo_iso_4217_pk} - ${item.nombre_y_origen}") },
                                        onClick = {
                                            unidad_monetaria_seleccionada = item.codigo_iso_4217_pk
                                            unidad_monetaria =
                                                "${item.codigo_iso_4217_pk} - ${item.nombre_y_origen}"
                                            expander = false
                                        }
                                    )

                                }
                            }
                        }
                    }
                )
            }
        },

        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = { onDismissFromAgregarServicioYPrecioDialog() },
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {

            // 1_AGREGAR PRECIO
            mPrecioViewModel.actualizarFechaYHora()
            Log.i("_xTEST_FROM_DIALOG", uiState_PrecioViewModel.fecha_y_hora)

            mPrecioViewModel.agregarPrecioUseCase(
                PrecioEntity(
                    precio_pk = precio_servicio_value.toFloat(),
                    //fecha_hora_creacion = uiState_PrecioViewModel.fecha_y_hora
                    fecha_hora_creacion = mPrecioViewModel.uiState.value.fecha_y_hora,
                )
            )

            // OBTENER EL PRECIO MAS RECIENTE

            if(precio_servicio_value.toFloat() != null){
                //2_AGREGAR SERVICIO Y SU PRECIO
                mServicioYPrecioViewModel.actualizarFechaYHora()

                mServicioYPrecioViewModel.agregarServicioYPrecioUseCase(
                    ServicioYPrecioEntity(
                        id_registro = 0,
                        id_servicio_fk = servicio_seleccionado,
                        //precio_fk = precio_servicio_value.toFloat(),
                        precio_fk = precio_servicio_value.toFloat(),
                        codigo_iso_4217_fk = unidad_monetaria_seleccionada,
                        precio_activo = true,
                        //fecha_hora_creacion = uiState_ServicioViewModel.fecha_y_hora
                        fecha_hora_creacion = mServicioYPrecioViewModel.uiState.value.fecha_y_hora
                    )
                )
            }

            onDismissFromAgregarServicioYPrecioDialog()

        },
        enabled_btn1 = true,
        enabled_btn2 = true,
        modifier_content_header = Modifier
            .fillMaxWidth()
            .size(150.dp),

        modifier_content_action_buttons = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}
