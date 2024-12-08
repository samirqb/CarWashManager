package samirqb.carwashmanager.app.ui.components.custom.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayer1P
import samirqb.carwashmanager.app.ui.components.custom.layouts.VLayout3P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextLabel
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle


@Composable
fun HeaderDialogX3PWidget(
    header_icon_id: Int,
    header_text_titulo_id: Int,
    header_text_subtitulo_id: Int,
    header_text_consecutivo: Int,
    modifier: Modifier = Modifier
) {
    sSurface(
        modifier = modifier,
        color =  MaterialTheme.colorScheme.secondaryContainer
    ) {
        VLayout3P(

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

            content3 = {
                HLayer1P(
                    content1 = { xTextLabel( text = stringResource(id = header_text_subtitulo_id, header_text_consecutivo )) },
                )
            },
        )
    }
}
