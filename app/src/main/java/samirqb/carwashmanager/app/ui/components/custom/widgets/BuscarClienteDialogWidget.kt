package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.base.inputs.sOutlinedTextField
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.templates.textbuttons.tHTxtButtonsX2
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P


@Composable
fun BuscarClienteDialogWidget(
    icon_id: Int = R.drawable.rounded_person_search_24,
    text_titulo: Int = R.string.txt_titulo_buscar_cliente,
    text_subtitulo: Int = R.string.txt_label_ingresar_id_cliente,
    txt_btn_1: Int,
    txt_btn_2: Int,
    modifier: Modifier = Modifier
) {
    sDialog(
        onDismissRequest = {},
        //content = {}
    ) {
        //sSurface(
        sCard(
            modifier = modifier.fillMaxWidth().size(299.dp),
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
                            var value by rememberSaveable { mutableStateOf("") }
                            sOutlinedTextField(
                                value = value,
                                onValueChange = {
                                    value = it
                                }
                            )
                        }
                    )
                },

                content3 = {
                    tHTxtButtonsX2(
                        modifier = Modifier.weight(0.5f).fillMaxWidth(),
                        txt_btn_1 = txt_btn_1,
                        on_click_boton_1 = {},
                        txt_btn_2 = txt_btn_2,
                        on_click_boton_2 = {},
                    )
                }
            )
        }
    }
}
