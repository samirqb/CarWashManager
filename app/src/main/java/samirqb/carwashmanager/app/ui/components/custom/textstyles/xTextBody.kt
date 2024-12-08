package samirqb.carwashmanager.app.ui.components.custom.textstyles

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import samirqb.carwashmanager.app.ui.components.base.outputs.sText


@Composable
fun xTextBody(
    text: String
) {
    sText(
        text = text,
        style = typography.bodyMedium
    )
}