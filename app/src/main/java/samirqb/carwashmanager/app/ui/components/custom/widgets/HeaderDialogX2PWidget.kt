package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle
import samirqb.carwashmanager.app.ui.layoutcomponets.VLayout2P

@Composable
fun HeaderDialogX2PWidget(
    header_icon_id: Int,
    header_text_titulo_id: Int,
    modifier: Modifier = Modifier
) {
    sSurface(
        modifier = modifier,
        color =  MaterialTheme.colorScheme.secondaryContainer
    ) {
        VLayout2P(

            modifier = Modifier.fillMaxWidth(),

            content1 = {
                sIcon(
                    image_vector_id = header_icon_id,
                    modifier = Modifier.size(33.dp)
                )
            },

            content2 = {
                xTextTitle( text = stringResource( id = header_text_titulo_id ))
            },
        )
    }
}
