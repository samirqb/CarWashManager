package samirqb.carwashmanager.app.ui.components.custom.textstyles

import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.outputs.sText

@Composable
fun xTextLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    sText(
        text = text,
        modifier = modifier,
        style = typography.labelMedium
    )
}