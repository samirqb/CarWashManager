package samirqb.carwashmanager.app.ui.components.custom.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import samirqb.carwashmanager.app.ui.components.base.layouts.sRow

@Composable
fun HLayout1P(
    content1: @Composable() (RowScope.() -> Unit),
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
){
    sRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = {
            content1()
        }
    )
}