package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.lcarwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun ClienteResumenCardWidget(
    icon_id: Int,
    matricula_vehiculo: String,
    categoria_vehiculo: String,

) {

    sSurface(
        modifier = Modifier.fillMaxWidth().size(59.dp),
        shape = RoundedCornerShape(15),
        color = MaterialTheme.colorScheme.tertiary,
    ) {
        HLayout2P(
            modifier = Modifier.fillMaxSize().padding(start = 13.dp),
            horizontalArrangement = Arrangement.Absolute.spacedBy(7.dp),
            //horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            content1 = {
                sSurface(
                    shape = RoundedCornerShape(45),
                    color = MaterialTheme.colorScheme.tertiaryContainer

                ) { sIcon(image_vector_id = icon_id,modifier = Modifier.padding(7.dp)) }
            },

            content2 = {
                VLayout2P(
                    content1 = { xTextTitle(text = matricula_vehiculo) },
                    content2 = { xTextLabel(text = categoria_vehiculo) },
                )
            },
        )
    }
}