package samirqb.carwashmanager.app.ui.components.base.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun sRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement. Horizontal = Arrangement.Start,
    verticalAlignment: Alignment. Vertical = Alignment.Top,
    content: @Composable() (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content,
    )
}