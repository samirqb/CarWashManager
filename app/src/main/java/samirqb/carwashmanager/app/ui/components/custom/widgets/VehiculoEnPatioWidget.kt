package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.R
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextBody
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun VehiculoEnPatioWidget(
    txt_matricula_vehiculo: String,
    txt_valor_servicio: String,
    modifier: Modifier = Modifier,
) {
    sSurface(
        modifier = modifier.size(width = 97.dp, height = 119.dp).padding(3.dp),
        //modifier = Modifier
        shape = RoundedCornerShape(13),
        //color = MaterialTheme.colorScheme.secondaryContainer
        color = MaterialTheme.colorScheme.onSecondary
        ) {
        VLayout2P(
            content1 = {
                sSurface(
                    modifier = Modifier.size(66.dp),
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    VLayout2P(
                        content1 = { sIcon(image_vector_id = R.drawable.rounded_directions_car_24 ) },
                        content2 = { xTextLabel(text = txt_matricula_vehiculo) },
                    )
                }
            },
            content2 = {
                HLayout2P(
                    content1 = { sIcon(image_vector_id =  R.drawable.rounded_attach_money_24) },
                    content2 = { xTextBody( text = txt_valor_servicio) },
                )
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun VehiculoEnPatioWidgetPreview(){
    VehiculoEnPatioWidget(
        txt_matricula_vehiculo = "NAL636",
        txt_valor_servicio = "12000",
    )
}
