package samirqb.carwashmanager.app.ui.components.custom.textstyles

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import samirqb.carwashmanager.app.ui.components.base.outputs.sText


@Composable
fun xTextBody(
    text: String,
    color: Color = Color.Unspecified,
) {
    sText(
        text = text,
        color = color,
        style = typography.bodyMedium
    )
}