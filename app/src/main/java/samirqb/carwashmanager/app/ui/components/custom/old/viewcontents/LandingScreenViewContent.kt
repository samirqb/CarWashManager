package samirqb.carwashmanager.app.ui.components.custom.old.viewcontents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.custom.widgets.EstadoCajaWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.ServiciosActivosWidget
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun LandingScreenViewContent(
    txt_body_fecha: String,
    txt_body_hora: String,
    txt_body_base_apertura: String,
    txt_body_acumulado_ventas: String,
    lista_servicios_activos:List<String>,
    content_padding:PaddingValues,
) {

    VLayout2P(
        modifier = Modifier.padding(content_padding),
        content1 = {
            EstadoCajaWidget(
                txt_body_fecha = txt_body_fecha,
                txt_body_hora = txt_body_hora,
                txt_body_base_apertura = txt_body_base_apertura,
                txt_body_acumulado_ventas = txt_body_acumulado_ventas,
                btn_ver_nomina_onClick = {},
            )
        },

        content2 = {
            ServiciosActivosWidget(
                lista_servicios_activos = lista_servicios_activos
            )
        },

    )
}