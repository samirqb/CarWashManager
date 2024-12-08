package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.inputs.sIconButton
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextHeadLine

@Composable
fun EstadoCajaWidget(
    txt_body_fecha: String,
    txt_body_hora: String,
    txt_body_base_apertura: String,
    txt_body_acumulado_ventas: String,
    btn_ver_nomina_onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    VLayout3P(
        content1 = {
            Spacer( modifier = Modifier.size(21.dp) )

            HLayout2P(
                content1 = {
                    sSurface(
                        modifier = Modifier.fillMaxWidth().size(39.dp).weight(5f),
                        color = Color.Transparent
                    ) {
                        xTextHeadLine( text = stringResource(id = R.string.txt_headline_estadocaja))
                    }
                },
                content2 = {
                    sIconButton(
                        onClick = btn_ver_nomina_onClick,
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        content = {
                            sIcon(image_vector_id = R.drawable.rounded_server_person_24)
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        ),
                    )
                },
            )

            Spacer( modifier = Modifier.size(7.dp) )
        },

        content2 = {
            FechaYHoraWidget(
                txt_body_fecha = txt_body_fecha,
                txt_body_hora = txt_body_hora,
            )
            Spacer( modifier = Modifier.size(7.dp) )
        },

        content3 = {
            BaseAperturaYAcumuladoVentasWidget(
                txt_body_base_apertura = txt_body_base_apertura,
                txt_body_acumulado_ventas = txt_body_acumulado_ventas,
            )
        },
    )
}