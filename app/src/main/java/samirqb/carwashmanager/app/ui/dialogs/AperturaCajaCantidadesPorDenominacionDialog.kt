package samirqb.carwashmanager.app.ui.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.widgets.AgregarCantPorDenominacionWidget
import samirqb.carwashmanager.app.ui.components.custom.widgets.TotalDineroAperturaCierreCajaWidget
import samirqb.carwashmanager.app.ui.templates.scaffoldsanddialogs.tDialogScaffoldM1
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun AperturaCajaCantidadesPorDenominacionDialog(
    onNavigateToAperturaCajaConfirmacionDialog: () -> Unit,
    onDismissFromAperturaCajaCantidadesPorDenominacionDialog: () -> Unit,
) {

    tDialogScaffoldM1(
        modifier_content1 = Modifier
            .fillMaxWidth()
            .size(150.dp),
        modifier_content3 = Modifier
            .fillMaxWidth()
            .size(50.dp),
        header_icon_id = R.drawable.rounded_point_of_sale_24,
        header_text_titulo_id = R.string.txt_titulo_apertura_de_caja,
        header_text_subtitulo_id = R.string.txt_label_numero_de_apertura,
        header_text_consecutivo = 1101,
        content_dialg_body = {
            sSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(432.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                VLayout2P(
                    content1 = {
                        TotalDineroAperturaCierreCajaWidget(
                            txt_body_suma_total_denominaciones = "87000",
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(90.dp)
                                //.weight(0.9f)
                                ,
                        )
                    },

                    content2 = {
                        AgregarCantPorDenominacionWidget(
                            modifier = Modifier
                                .fillMaxWidth().size(342.dp)
                                //.weight(2f)
                                ,
                            lista_denominaciones = listOf(
                                "50",
                                "100",
                                "200",
                                "500",
                                "1.000",
                                "2.000",
                                "5.000"
                            ),
                            //value = value,
                            //onValueChange = {value = it}
                        )
                    },
                )
            }
        },

        boton_txt_1 = R.string.txt_label_salir,

        on_click_boton_1 = {
            onDismissFromAperturaCajaCantidadesPorDenominacionDialog()
        },

        boton_txt_2 = R.string.txt_label_siguiente,

        on_click_boton_2 = {
            onNavigateToAperturaCajaConfirmacionDialog()
        },
    )
}
