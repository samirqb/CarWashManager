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
import androidx.lifecycle.viewmodel.compose.viewModel
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textfields.config.SeparadorDeMiles
import samirqb.carwashmanager.app.ui.components.custom.textfields.xOutlinedTextField
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2
import samirqb.carwashmanager.app.viewmodels.DenominacionMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.MonedaViewModel
import samirqb.carwashmanager.app.viewmodels.TipoMonedaViewModel
import samirqb.carwashmanager.app.viewmodels.UnidadMonetariaViewModel
import samirqb.lib.caja.entidades.DenominacionMonedaEntity
import samirqb.lib.caja.entidades.MonedaEntity
import samirqb.lib.helpers.FechaYHora
import samirqb.lib.helpers.ValidarEntradasRegex

//@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarMonedaDialog(
    mUMVM: UnidadMonetariaViewModel = viewModel(),
    mTMVM: TipoMonedaViewModel = viewModel(),
    mMVM: MonedaViewModel = viewModel(),
    mDMVM: DenominacionMonedaViewModel = viewModel(),
    onDismissFromAgregarMonedaDialog: () -> Unit,

    ) {

    val mValidarEntradasRegex = ValidarEntradasRegex()

    mUMVM.leerTodo()
    mTMVM.leerTodo()
    mMVM.leerTodo()
    mDMVM.leerTodo()

    val uiState_UMVM by mUMVM.uiState.collectAsState()
    val uiState_TMVM by mTMVM.uiState.collectAsState()
    val uiState_DMVM by mDMVM.uiState.collectAsState()
    val uiState_MVM by mMVM.uiState.collectAsState()

    val lUM = uiState_UMVM.lista_unidades_monetarias
    val lTM = uiState_TMVM.todoslosTiposMoneda
    val lDM = uiState_DMVM.todasLasDenominacionesMoneda
    val lM = uiState_MVM.todasLasMonedas

    var unidad_monetaria_seleccionada by rememberSaveable { mutableStateOf("") }
    var denominacion_value by rememberSaveable { mutableStateOf("") }
    var tipo_moneda_seleccionado by rememberSaveable { mutableStateOf("") }


    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_attach_money_24,
        header_text_titulo_id = R.string.txt_titulo_agregar_moneda,
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
                                onDismissRequest = {},
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ) {
                                lUM.forEachIndexed { index, item ->
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

                    },

                    content2 = {
                        sSurface() {
                            var value by rememberSaveable { mutableStateOf("") }
                            //denominacion_value
                            xOutlinedTextField(
                                value = value,
                                onValueChange = {

                                    if(mValidarEntradasRegex.validarNumeros(it)) {
                                        value = it
                                        denominacion_value = it
                                    } else {
                                        value = ""
                                        denominacion_value = ""
                                    }

                                },
                                visualTransformation = SeparadorDeMiles()
                            )
                        }
                    },

                    content3 = {


                        //var tipo_moneda_seleccionado by rememberSaveable { mutableStateOf("") }
                        var expander by rememberSaveable { mutableStateOf(false) }

                        sSurface {
                            if (tipo_moneda_seleccionado.isBlank() or tipo_moneda_seleccionado.isEmpty()) {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = R.string.txt_body_elige_un_tipo,
                                )
                            } else {
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = tipo_moneda_seleccionado,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = {},
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ) {
                                lTM.forEachIndexed { index, item ->

                                    //item.
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${item.tipo_pk}") },
                                        onClick = {
                                            tipo_moneda_seleccionado = item.tipo_pk
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
        on_click_boton_1 = {
            onDismissFromAgregarMonedaDialog()
        },
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {

            val fecha_y_hora = FechaYHora()

            mDMVM.agregarDenominacion(

                mTEntity = DenominacionMonedaEntity(
                    denominacion_pk = denominacion_value.toFloat(),
                    fecha_hora_creacion = fecha_y_hora.now()
                )
            )

            mMVM.agregarMoneda(
                mTEntity = MonedaEntity(
                    codigo_iso_4217_fk = unidad_monetaria_seleccionada,
                    denominacion_fk = denominacion_value.toFloat(),
                    tipo_fk = tipo_moneda_seleccionado,
                    fecha_hora_creacion = fecha_y_hora.now(),
                )
            )
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