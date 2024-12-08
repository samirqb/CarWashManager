package samirqb.carwashmanager.app.ui.templates.iconsandtexts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import samirqb.carwashmanager.app.ui.components.base.outputs.sIcon
import samirqb.carwashmanager.app.ui.components.base.containers.sSurface
import samirqb.carwashmanager.app.ui.components.custom.layouts.HLayout2P
import samirqb.carwashmanager.app.ui.components.custom.textstyles.xTextTitle

@Composable
fun tHIconAndText(
    image_vector_id:Int,
    txt_body:Int,
    full_surface_modifier: Modifier = Modifier.fillMaxWidth().size(55.dp),
    full_surface_shape: Shape = RoundedCornerShape(13),
    full_surface_color: Color = MaterialTheme.colorScheme.surfaceContainer
) {

    var ROUNDED_CORNER_SHAPE = 13

    sSurface(
        modifier = full_surface_modifier,
        shape = full_surface_shape,
        color = full_surface_color
    ){

        HLayout2P(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(11.dp, alignment = Alignment.CenterHorizontally),
            //modifier = Modifier.
            content1 = {
                sSurface(
                    shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ){
                    sIcon(image_vector_id = image_vector_id)
                }
            },
            content2 = {
                sSurface(

                    shape = RoundedCornerShape(ROUNDED_CORNER_SHAPE),
                    color = MaterialTheme.colorScheme.surfaceContainer
                ){
                    xTextTitle(text = stringResource(id = txt_body) )
                }
            },
        )
    }
}
