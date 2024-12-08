package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.base.containers.sDropdownMenu
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.inputs.sTextButton
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.textbuttons.tHTxtButtonsX2
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun SeleccionarVehiculoCategoriaWidget(
    icon_id: Int = R.drawable.rounded_category_24,
    text_titulo: Int = R.string.txt_titulo_categoria,
    text_subtitulo: Int = R.string.txt_label_seleccione_categoria,
    lista_categorias: List<String>,
    modifier: Modifier = Modifier
) {
    sDialog(
        onDismissRequest = {},
        //content = {}
    ) {
        //sSurface(
        sCard(
            modifier = modifier
                .fillMaxWidth()
                .size(299.dp),
            //color =  MaterialTheme.colorScheme.secondaryContainer
        ) {
            VLayout3P(

                modifier = Modifier.fillMaxWidth(),

                content1 = {
                    GenericHeaderDialogWidget(
                        icon_id = icon_id,
                        text_titulo = text_titulo,
                        modifier = Modifier.weight(1f)
                    )
                },
                content2 = {
                    VLayout2P(
                        modifier = Modifier.weight(1f),
                        content1 = {
                            xTextLabel(text = stringResource(id = text_subtitulo))
                        },

                        content2 = {

                            var txt by rememberSaveable { mutableStateOf("lista del catogorias") }

                            var expander by rememberSaveable { mutableStateOf(false) }

                            sTextButton(
                                onClick = {
                                    expander = true
                                }
                            ) {
                                HLayout2P(
                                    content1 = {
                                        xTextBody(text = txt)
                                    },
                                    content2 = {
                                        sIcon(image_vector_id = R.drawable.rounded_arrow_drop_down_24)
                                    },
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
                    )
                },

                content3 = {
                    tHTxtButtonsX2(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        txt_btn_1 = R.string.txt_label_cancelar,
                        on_click_boton_1 = {},
                        txt_btn_2 = R.string.txt_label_aceptar,
                        on_click_boton_2 = {},
                    )
                }
            )
        }
    }
}