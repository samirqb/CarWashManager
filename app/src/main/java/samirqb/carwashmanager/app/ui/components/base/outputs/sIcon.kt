package samirqb.carwashmanager.app.ui.components.base.outputs

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun sIcon(
    image_vector_id: Int,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = ImageVector.vectorResource(id = image_vector_id),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}