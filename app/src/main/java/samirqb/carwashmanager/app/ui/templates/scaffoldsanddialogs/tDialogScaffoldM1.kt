package samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.templates.textbuttons.tHTxtButtonsX2
import samirqb.carwashmanager.app.ui.components.custom.widgets.HeaderDialogX3PWidget

@Composable
fun tDialogScaffoldM1(
    header_icon_id: Int,
    header_text_titulo_id: Int,
    header_text_subtitulo_id: Int,
    header_text_consecutivo: Int,
    content_dialg_body: @Composable() (ColumnScope.() -> Unit),
    boton_txt_1:Int,
    on_click_boton_1: () -> Unit,
    boton_txt_2:Int,
    on_click_boton_2: () -> Unit,
    //onDismissRequestDialog: () -> Unit,
    modifier_content1: Modifier = Modifier,
    modifier_content2: Modifier = Modifier,
    modifier_content3: Modifier = Modifier,
) {
    //val openDialog = remember { mutableStateOf(openDialog) }
    val openDialog = remember { mutableStateOf(true) }

    //if (openDialog.value) return
    if (openDialog.value){
        sDialog(
            onDismissRequest = {
                //openDialog.value = false
                //on_click_boton_1()
            },
            content = {
                sCard {
                    VLayout3P(
                        content1 = {
                            HeaderDialogX3PWidget(
                                modifier = modifier_content1,
                                header_icon_id = header_icon_id,
                                header_text_titulo_id = header_text_titulo_id,
                                header_text_subtitulo_id = header_text_subtitulo_id,
                                header_text_consecutivo = header_text_consecutivo,
                            )
                        },
                        content2 = {
                            content_dialg_body()
                        },

                        // BOTONERA DE ACCION x2
                        content3 = {
                            tHTxtButtonsX2(
                                modifier = modifier_content3,
                                txt_btn_1 = boton_txt_1,
                                on_click_boton_1 = {
                                    openDialog.value = false
                                    on_click_boton_1()
                                },
                                txt_btn_2 = boton_txt_2,
                                on_click_boton_2 = on_click_boton_2,
                            )
                        },
                    )
                }
            }
        )
    }



    /*
    when{
        openDialog.value -> {

        }
    }
    */
}