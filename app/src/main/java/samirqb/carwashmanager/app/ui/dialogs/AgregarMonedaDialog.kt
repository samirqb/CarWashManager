package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import samirqb.carwashmanager.app.ui.components.base.inputs.sOutlinedTextField
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.templates.iconsandtexts.tHTextAndIcon
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2

@Composable
fun AgregarMonedaDialog(
    onDismissFromAgregarMonedaDialog:()->Unit,
){

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

                        var txt by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }

                        var lista_categorias = listOf("COP","USD")

                        sSurface {
                            if(txt.isBlank() or txt.isEmpty()){
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
                            }else{
                                tHTextAndIcon(
                                    full_surface_modifier = Modifier
                                        .fillMaxWidth()
                                        .size(55.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures { expander = true }
                                        },
                                    //image_vector_ini_id = R.drawable.round_monetization_on_24,
                                    image_vector_fin_id = R.drawable.rounded_arrow_drop_down_24,
                                    txt_body = txt,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = {},
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ){
                                lista_categorias.forEachIndexed{ index, categoria ->
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${categoria}") },
                                        onClick = {
                                            txt = categoria
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
                            sOutlinedTextField(
                                value = value,
                                onValueChange = { value = it },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },

                    content3 = {

                        var txt by rememberSaveable { mutableStateOf("") }

                        var expander by rememberSaveable { mutableStateOf(false) }

                        var lista_categorias = listOf("BILLETE","MONEDA","VIRTUAL")

                        sSurface {
                            if(txt.isBlank() or txt.isEmpty()){
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
                                    txt_body = txt,
                                )
                            }

                            DropdownMenu(
                                expanded = expander,
                                onDismissRequest = {},
                                modifier = Modifier.size(width = 266.dp, height = Dp.Unspecified)
                            ){
                                lista_categorias.forEachIndexed{ index, categoria ->
                                    DropdownMenuItem(
                                        text = { xTextBody(text = "${index} - ${categoria}") },
                                        onClick = {
                                            txt = categoria
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
        on_click_boton_2 = {},
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),
        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
        //modifier_content3: Modifier = Modifier
    )
}