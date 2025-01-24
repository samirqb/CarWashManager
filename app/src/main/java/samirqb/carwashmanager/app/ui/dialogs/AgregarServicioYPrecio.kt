package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM2

@Composable
fun AgregarServicioYPrecio() {

    tDialogScaffoldM2(
        header_icon_id = R.drawable.rounded_local_car_wash_24 ,
        header_text_titulo_id = R.string.txt_titulo_agregar_servicio,
        content_dialg_body = {},
        boton_txt_1 = R.string.txt_label_cancelar,
        on_click_boton_1 = {},
        boton_txt_2 = R.string.txt_label_agregar,
        on_click_boton_2 = {},
        enabled_btn1 = true,
        enabled_btn2 = true,
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),

        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
    )
}