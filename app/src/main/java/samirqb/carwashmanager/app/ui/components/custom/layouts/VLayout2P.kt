package samirqb.carwashmanager.app.ui.layoutcomponets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.layouts.sColumn

@Composable
fun VLayout2P(
    content1: @Composable() (ColumnScope.() -> Unit),
    content2: @Composable() (ColumnScope.() -> Unit),
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement. Vertical = Arrangement.Center,
    horizontalAlignment: Alignment. Horizontal = Alignment.CenterHorizontally,
) {
    sColumn(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = {
            content1()
            content2()
        }
    )
}