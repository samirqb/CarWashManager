package samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.containers.sCard
import samirqb.carwashmanager.app.ui.components.base.containers.sDialog
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.widgets.HeaderDialogX2PWidget
import samirqb.carwashmanager.app.ui.templates.textbuttons.tHTxtButtonsX2

@Composable
fun tDialogScaffoldM2(
    header_icon_id: Int,
    header_text_titulo_id: Int,
    content_dialg_body: @Composable() (ColumnScope.() -> Unit),
    boton_txt_1:Int,
    on_click_boton_1: () -> Unit,
    boton_txt_2:Int,
    on_click_boton_2: () -> Unit,
    enabled_btn1:Boolean = true,
    enabled_btn2:Boolean = true,
    modifier_content_header: Modifier = Modifier,
    modifier_content_action_buttons: Modifier = Modifier,
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
                            HeaderDialogX2PWidget(
                                modifier = modifier_content_header,
                                header_icon_id = header_icon_id,
                                header_text_titulo_id = header_text_titulo_id,
                            )
                        },
                        content2 = {
                            content_dialg_body()
                        },

                        // BOTONERA DE ACCION x2
                        content3 = {
                            tHTxtButtonsX2(
                                modifier = modifier_content_action_buttons,
                                txt_btn_1 = boton_txt_1,
                                on_click_boton_1 = {
                                    openDialog.value = false
                                    on_click_boton_1()
                                },
                                txt_btn_2 = boton_txt_2,
                                on_click_boton_2 = on_click_boton_2,
                                enabled_btn1 = enabled_btn1,
                                enabled_btn2 = enabled_btn2,
                            )
                        },
                    )
                }
            }
        )
    }
}