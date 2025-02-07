package samirqb.carwashmanager.app.ui.components.custom.textstyles

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import samirqb.carwashmanager.app.ui.components.base.outputs.sText

@Composable
fun xTextTitle(
    text: String,
    color: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    sText(
        text = text,
        modifier = modifier,
        color = color,
        style = typography.titleMedium
    )
}